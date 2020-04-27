package ru.itis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

public class DatabaseAppender extends AppenderSkeleton implements Appender {

    @Getter
    @Setter
    protected String databaseURL = "jdbc:postgresql://localhost:5433/r";

    @Getter
    @Setter
    protected String databaseUser = "postgres";

    @Getter
    @Setter
    protected String databasePassword = "543216789";

    protected Connection connection = null;

    @Getter
    protected String sqlStatement = "";

    @Getter
    protected int bufferSize = 1;

    protected ArrayList buffer;
    protected ArrayList removes;

    @Getter
    @Setter
    private boolean locationInfo = false;

    public DatabaseAppender() {
        super();
        buffer = new ArrayList(bufferSize);
        removes = new ArrayList(bufferSize);
    }

    public void append(LoggingEvent event) {
        event.getNDC();
        event.getThreadName();
        event.getMDCCopy();
        if (locationInfo) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        buffer.add(event);

        if (buffer.size() >= bufferSize)
            flushBuffer();
    }

    protected String getLogStatement(LoggingEvent event) {
        return getLayout().format(event);
    }


    protected void execute(String sql) throws SQLException {

        Connection con = null;
        Statement stmt = null;

        try {
            con = getConnection();

            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            closeConnection(con);
        }
    }

    protected void closeConnection(Connection con) {
    }

    protected Connection getConnection() throws SQLException {
        if (!DriverManager.getDrivers().hasMoreElements())
            setDriver("sun.jdbc.odbc.JdbcOdbcDriver");

        if (connection == null) {
            connection = DriverManager.getConnection(databaseURL, databaseUser,
                    databasePassword);
        }

        return connection;
    }

    public void close()
    {
        flushBuffer();

        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            errorHandler.error("Error closing connection", e, ErrorCode.GENERIC_FAILURE);
        }
        this.closed = true;
    }

    public void flushBuffer() {
        //Do the actual logging
        removes.ensureCapacity(buffer.size());
        for (Iterator i = buffer.iterator(); i.hasNext();) {
            LoggingEvent logEvent = (LoggingEvent)i.next();
            try {
                String sql = getLogStatement(logEvent);
                execute(sql);
            }
            catch (SQLException e) {
                errorHandler.error("Failed to excute sql", e,
                        ErrorCode.FLUSH_FAILURE);
            } finally {
                removes.add(logEvent);
            }
        }
        buffer.removeAll(removes);
        removes.clear();
    }


    public void finalize() {
        close();
    }

    public boolean requiresLayout() {
        return true;
    }

    public void setSql(String s) {
        sqlStatement = s;
        if (getLayout() == null) {
            this.setLayout(new PatternLayout(s));
        }
        else {
            ((PatternLayout)getLayout()).setConversionPattern(s);
        }
    }

    public void setBufferSize(int newBufferSize) {
        bufferSize = newBufferSize;
        buffer.ensureCapacity(bufferSize);
        removes.ensureCapacity(bufferSize);
    }

    public void setDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (Exception e) {
            errorHandler.error("Failed to load driver", e,
                    ErrorCode.GENERIC_FAILURE);
        }
    }
}

