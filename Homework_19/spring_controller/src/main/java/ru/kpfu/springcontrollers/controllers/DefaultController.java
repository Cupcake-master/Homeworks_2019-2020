package ru.kpfu.springcontrollers.controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

  @RequestMapping
  public String index(ModelMap map) {
    map.put("viewVariable", "Index action");
    return "tests";
  }
  
  @RequestMapping("/s")
  public String simple(ModelMap map) {
    map.put("viewVariable", "Just simple action");
    return "tests";
  }
  
  @RequestMapping( { "/s1" , "/s2" } )
  public String simpleMultiple(ModelMap map) {
    map.put("viewVariable", "Just simple action for 2 addresses");
    return "tests";
  }
  
  @RequestMapping("/rb")
  @ResponseBody
  public String responseBody(){
    return "The only text on a page is this text - @ResponseBody annotation.";
  }
  
  @RequestMapping("/writer")
  public void writer(java.io.Writer writer) throws IOException{
    writer.write("This is plain/text done with java.io.Writer");
  }
  
  @RequestMapping("/rs")
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public String responseStatus(ModelMap map){
    map.put("viewVariable", "What do you want from me? I'm a teapot!");
    return "tests";
  }

  @RequestMapping(
    value = "/rmb",
    method = RequestMethod.GET,
    headers = "!user-agent=Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:36.0) Gecko/20100101 Firefox/36.0",
    params = "secret_param=1"
  )
  public String requestMappingBunch(ModelMap map) {
    map.put("viewVariable", "Page with secret_param for not Mozilla 36 on Linux");
    return "tests";
  }

  @RequestMapping("/rp")
  public String requestParam(@RequestParam String param, ModelMap map) {
    map.put("viewVariable", "Request parameter:<br>" + param );
    return "tests";
  }

  @RequestMapping("/rpb")
  public String requestParamBanch(
    @RequestParam(
      value        = "special_name",
      required     = false,
      defaultValue = "Default value"
    ) String param,
    ModelMap map
  ) {
    map.put("viewVariable", "Request parameter:<br>" + param );
    return "tests";
  }
  
  @RequestMapping("/pv/{param}")
  public String pathVariable(@PathVariable String param, ModelMap map){
    map.put("viewVariable", "Path variable:<br>" + param );
    return "tests";
  }
  
  @RequestMapping("/pvb/{param_special_name}/{param2}/path")
  public String pathVariableBunch(
    @PathVariable(
      value = "param_special_name"
    ) String param,
    @PathVariable String param2,
    ModelMap map
  ){
    map.put("viewVariable", "Path variable:<br>" + param );
    return "tests";
  }
  
  @RequestMapping("/**")
  public Object requestHeader(@RequestHeader("user-agent") String param){
    Pattern pattern = Pattern.compile(".+Edge.+");
    Matcher matcher = pattern.matcher(param);
    if (matcher.matches()) return HttpStatus.BAD_REQUEST;
    else return "tests";
  }

  @RequestMapping("/all")
  public String outputAllHeaders(@RequestHeader HttpHeaders httpHeader, ModelMap map){
    map.put("viewVariable", httpHeader.toString());
      System.out.println(httpHeader.toString());
    return "tests";
  }

  @GetMapping("/calc")
  public String getPage(){
    return "calc";
  }

  @PostMapping("/calc")
  public String count(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    String s1 = request.getParameter("firstOperator");
    String s2 = request.getParameter("secondOperator");
    String s3 = request.getParameter("operand");
    double x = Double.parseDouble(s1);
    double y = Double.parseDouble(s2);
    double result = 0;
    char z = s3.charAt(0);
    if (z == '+') result = x + y;
    if (z == '-') result = x - y;
    if (z == '*') result = x * y;
    if (z == '/') result = x / y;
    redirectAttributes.addFlashAttribute("viewVariable", "Answer: " + result);
    return "redirect:"  + request.getContextPath() + "/calc";
  }
}
