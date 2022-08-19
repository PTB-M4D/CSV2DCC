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
In Column B, put all the relevant data as shown in the example below in Figure 2.
</p>

![Image 1](docs\Img1.png)

<p align="justify">
Figure 1. An example Substitution File
</p>



