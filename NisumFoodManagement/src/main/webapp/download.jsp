<%    
System.out.println("");
String name=request.getParameter("uname"); 
System.out.println("");
out.print("welcome "+name);   
  String filename = "home.jsp";   
  String filepath = "/Users/nisum/Documents/Excel_Directory/";   
  response.setContentType("APPLICATION/OCTET-STREAM");   
  response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
  
  java.io.FileInputStream fileInputStream=new java.io.FileInputStream(name);  
            
  int i;   
  while ((i=fileInputStream.read()) != -1) {  
    out.write(i);   
  }   
  fileInputStream.close();   
%>   