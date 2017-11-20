
#Check writer code repository

Summary:
------------------------
- Developed amount to text conversion as Ajax application. 
- Two views created for amount conversion.
- The first view can be used to convert amount less than maximum integer size into text. 
- The second view can be utilized to convert larger amount into words. 
- The application is packaged as jar file.

Application Detail:
------------------------

1.	First View
       
       - Page converts amount less than maximum integer size
       - The page accepts input in USD Format and convert into words. (i.e. includes commas and dot)
       - The errors message will be throwed when the input exceeds maximum input size.
       
       URL - http://localhost:8080/

2.  Second View         

       - This Page accepts input larger than int and convert into words.
       - The page accepts input in USD Format and convert into words.
       - Have configured maximum amount as a 1 hundred billion. 
       - Error message will be displayed when the user input exceeds the maximum input value.
       - The application can be extended to support for larger input value.
       
       URL- http://localhost:8080/largeAmountConversion
       
 Application Installation:
 -------------------------
 1) Download the code from the repository.
 2) Navigate to folder where application is dowloaded into the local machine.
 3) Execute the Jar file through command line.
   Example: 
      java -jar target\SpringCurrencyTextApp-1.00.jar
      
   NOTE:
   
   If maven install throws error in eclipse , configure JDK executable property settings in pom.xml file.
   
   executable : C:\Program Files\Java\jdk1.8.0_77\bin\javac
   
     
