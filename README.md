# CSV2DCC 

The purpose of the CSV2DCC application is to embed metrology data held within a CSV (actually a semicolon separated) file into a Digital Calibration Certifcate (DCC) XML file. It is expected that this application will be of use in the generation of DCCs in the production environment.

To use CSV2DCC, the User will first need to prepare a DCC that is suitable for their work, this will be the "Input DCC" for the CSV2DCC application.

(The Input DCC will probably contain dummy, typical data, but this is not absolutely necessary)

To generate the Input DCC, please refer to the core DCC documentation at https://www.ptb.de/dcc/ for full details on how to create a DCC suitable for a particular case. Please also refer to the GEMIMEG Tool which provides an easy way to generate a DCC XML file using a GUI for a number of fields of metrology including Temperature and Humidity.

Once the Input DCC has been generated, please refer to the "CSV2DCC User Guide" for instructions on how to create a Parameterised version of the Input DCC, and how to create the CSV (semicolon separated) file which will contain the metrology information to be embedded into the DCC.

The working pre-release version section of this site contains information on how to set CSV2DCC up on a windows PC running JRE 1.8 or later.

The first release of CSV2DCC can be found at https://github.com/PTB-M4D/CSV2DCC/releases/tag/v0.1.0

# CSV2DCC User Guide

## Introduction

<p align="justify">
The purpose of the CSV2DCC application is to copy calibration result information from a CSV file* into the relevant places within a DCC template. The application has been written to be applicable to all types of XML type DCC file.
</p>

<p align="justify">
* Actually a semi-colon separated file will be used to avoid regional specific use of commas and decimal points across Europe. For example
</p>

<p align="justify">
UK representation	 	1,200,300.456
</p>



<p align="justify">
Deutsch representation		1.200.300,456
</p>

<p align="justify">
<b>Important Note:-</b> If every DCC you generate (excluding the results) is unique, with differing structure and length, then CSV2DCC in its current version is unlikely to be of use. to you.  If you generate many DCCs with the same structure (but differing results) then CSV2DCC will probably be of use to you. 
</p>  

## Installation

<p align="justify">
Please refer to the release section of this repository for full instructions on how to install CSV2DCC.
</p>  

## How to use CSV2DCC

<p align="justify">
<b>Please Note:-</b> The example files used in this document are available in the “ExamplesFIles” folder, under “SimpleExampleFiles” so you do not need to create these files, in order to start using the application. There is also a Temperature DCC example (Parameterised and Substitution files) stored under “TemperatureExample”. 
</p>  

<p align="justify">
In order to explain the principles of how to use the CSV2DCC application a very small XML file will be used as an example. 
</p>

<p align="justify">
Consider the following example xml file
</p>


```
<?xml version="1.0"?>
<Employee_Info
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:noNamespaceSchemaLocation="employee.xs">
  <Employee  Employee_Number="105">
    <Name>Masashi Okamura</Name>
    <Department>Design Department</Department>
    <Telephone>03-1452-4567</Telephone>
    <Email>okamura@xmltr.co.jp</Email>
  </Employee>
</Employee_Info>

```

<p align="justify">
Script 1. A basic XML file containing data
</p>

<p align="justify">
Although there is only a small amount data, this XML file is complete and for the purposes of demonstrating how CSV2DCC works is adequate. 
</p>

<p align="justify">
We now show how to use the CSV2DCC application in 3 steps (not quite to heaven but as close as we can get! :o) ).
</p>

## Step 1. Create a Parameterised XML DCC file

<p align="justify">
First we need to convert this example into a parameterised version as described below.
</p>

<p align="justify">
We will replace all items of data with separate codes, as shown below in Script 2. By comparing Script 1 and Script 2 you will see that all data in the elements has been replaced with a code; and also the single example of an attribute "Employee_Number" with data (105) has also been replaced.  Let's call this file "EmployeePar.xml". It is recommended that "Par" is placed in the filename to indicate this is a parameterised file. (CSV2DCC will check to see that a parameter file includes the string "par" in the filename). Doing this makes it easier to see which files are the Parameterised versions.
</p>

```
<?xml version="1.0"?>
<Employee_Info
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:noNamespaceSchemaLocation="employee.xs">
  <Employee  Employee_Number="XYX01">
    <Name>XYX02</Name>
    <Department>XYX03</Department>
    <Telephone>XYX04</Telephone>
    <Email>XYX05</Email>
  </Employee>
</Employee_Info>
```

<p align="justify">
Script 2. The basic file with the data replaced with codes creating a Parameterised version.
</p>

<p align="justify">
Each code consists of a Stub "XYX" and an incremented digit. The stub was chosen so that it is unique in the file. You can (and should) use any stub you like. Choose a stub where the sequence of letters does not appear anywhere in the original file.
</p>

<p align="justify">
The stub text can be any length and the incrementing digit sequence can be as long as required. So for example,
</p>

<p align="justify">
XYZYLPLMHBYGX0000000019
</p>

<p align="justify">
will work, but obviously probably not necessary as this will increase the amount of typing required and the increase the chance of making a typing mistake. Choose a length that works for you.
</p>

<p align="justify">
<b>Note:-</b> If you have a sequence of data in an element, say
</p>

```
<Promotion_Dates>1971  1980  1995</Promotion_Dates)
```

<p align="justify">
Simply replace the whole sequence with a single code e.g.
</p>

```
<Promotion_Dates>XYX55</Promotion_Dates)
```

## Step 2. Create a Data Substitution CSV file

<p align="justify">
We now need to create the substitution CSV file using "Excel" or "LibreCalc" or any similar spreadsheet application.
</p>

<p align="justify">
In a new "sheet" in your spreadsheet application, in Column A list all the codes you used in the parameterised XML file.
</p>

<p align="justify">
In Column B, put all the relevant data as shown in the example below in Figure 1.
</p>

<img src=".\docs\Img1.png" alt="Image1" title="Figure 1">

<p align="justify">
Figure 1. An example Substitution File
</p>

<p align="justify">
Please note that the entries in column B must all be text (XML files are by definition all text). It is necessary to 'force' Excel (or whichever application you are using) to treat numbers as text. This is done by placing an apostrophe (') in front of the number as shown in the figure 1 above. So instead of 105 the B1 cell contains '105.
</p>

<p align="justify">
You can easily see if an entry is being treated as a number (and not text) if it appears on the right hand side of the cell that contains it. So to check your file, simply scan down the Column B containing the substitution text and make sure all entries start from on the left hand side of the cell, as in the example above.
</p>

<p align="justify">
We'll save this as EmployeeSubs.csv. As was mentioned above, we need to actually use semicolons ";" as the separator and not commas. See Appendix A below for instructions on how to do this if you need to.
</p>

## Step 3. Running the application CSV2DCC

<p align="justify">
Now we are ready to use CSV2DCC. If you haven't done so already, please follow the instructions in the Releases section of this repository (???).
</p>

<p align="justify">
Start the application by opening windows explorer, navigate to the folder where you installed the application and double click on CSV2DCC.bat. The screen in figure 2 will appear.
</p>

<img src=".\docs\Img2.PNG" alt="Image2" title="Figure 2">

<p align="justify">
Figure 2. CSV2DCC - Main Screen
</p> 

<p align="justify">
Use the first two yellow "Browse" buttons to individually select the CSV file (EmployeeSubs.csv), and the parameter file (EmployeePar.xml). 
</p>

<p align="justify">
Using the 3rd yellow "Browse" button navigate to where you want the output to be saved and provide a name for the output file, e.g. "EmployeeDataOut.XML". You can select a file that already exists (Careful! not to overwrite the parameter file - there will be a warning but the application will not stop the parameter file from being overwritten).
</p>

<p align="justify">
Now click on the "Generate" green button and, if all goes well, you should receive a message telling you that the output file was created.

<p align="justify">
Use your preferred XML viewer (e.g. MS Edge) to open the newly created XML file (e.g. "EmployeeDataOut.XML"). You should see that the original data has now been put back into the XML file. 
</p>

<p align="justify">
So far we have not seen CSV2DCC do anything useful. All we have done is reproduce the original file, not very useful.
</p>

<p align="justify">
But now we can edit the substitution file (EmployeeSubs.csv), by putting new data into this file. So edit the data in EmployeeSubs.CSV, make changes to the contents of Column B which contains the data and save this as EmployeeSubs2.CSV. Rerun CSV2DCC using this second version of the substitution file and this time the output will use the new information in the CSV file. Again open the output file to confirm your data has been added to the output file.
</p>

## How can CSV2DCC be used in practice?

<p align="justify">
To actually use CSV2DCC in practice:

<p align="justify">
1. Develop a relevant DCC for your area of work (which will probably contain example data).
</p>

<p align="justify">
2. When you have an individual DCC file that is suitable, create a Parameterised version of this xml file replacing all the example data with codes (as described in Step 1 ).
</p>

<p align="justify">
3. In your current spreadsheet file that generates your results (assuming you use a spreadsheet application in your current process)  add a new sheet, call it "CSVOutput" for example, and add the codes that we used in Step 2 into Column A. In Column B of the CSVOutput sheet, you will need to develop the Excel to create the relevant data. Hopefully this will just be a matter of copying/linking information from other sheets in your spreadsheet, but this is very implementation dependent. 
</p>

<p align="justify">
4. 'Run' your process spreadsheet to ensure that the correct data is put into Column B of the CSVOutput sheet. Confirm that this has worked.
</p>

<p align="justify">
5. Run the Excel export wizard to output the CSVOutput sheet to create a CSV (actually semicolon separated) file.
</p>

<p align="justify">
6. Run CSV2DCC using the above parameterised DCC file and the CSV file to create an output DCC.
</p>

## I do not use a Spreadsheet, can I still use CSV2DCC?

<p align="justify">
Although many metrology practitioners use a spreadsheet to process raw data to create the final results, not everyone does.
</p>

<p align="justify">
You may instead use custom software to generate your results. If this is the case, in principle it is possible to add functionality to a custom implementation to output a CSV file. Please contact your local software engineer or provider to discuss this option.  This document should provide the developer with enough information to implement the necessary additional functionality to output a CSV file containing the relevant information. 
</p>

## Appendix A How to export Semicolon separated variables in Excel

<p align="justify">
<b>Note:-</b> This will change the way numbers are presented in the spreadsheet cells.
</p>

<ol>
  <li>Within Excel navigate to the "Excel  Options" screen</li>
  <li>Click on "Advanced"</li>
  <li>Under "Editing Options"
    <ol>
      <li>Uncheck the "Use System Separators" check box</li>
      <li>In the "Decimal separator" text box put a "," (i.e. a single comma)</li>
      <li>In the "Thousands separator" text box put a "." (i.e. a single full-stop)</li>
    </ol>
  </li>
</ol>


<p align="justify">
Now when you output to a csv file, semi-colon separators will be used.
</p>







