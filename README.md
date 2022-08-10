# CSV2DCC 

The purpose of the CSV2DCC application is to embed metrology data held within a CSV (actually a semicolon separated) file into a Digital Calibration Certifcate (DCC) XML file. It is expected that this application will be of use in the generation of DCCs in the production environment.

To use CSV2DCC, the User will first need to prepare a DCC that is suitable for their work, this will be the "Input DCC" for the CSV2DCC application.

(The Input DCC will probably contain dummy, typical data, but this is not absolutely necessary)

To generate the Input DCC, please refer to the core DCC documentation at https://www.ptb.de/dcc/ for full details on how to create a DCC suitable for a particular case. Please also refer to the GEMIMEG Tool which provides an easy way to generate a DCC XML file using a GUI for a number of fields of metrology including Temperature and Humidity.

Once the Input DCC has been generated, please refer to the "CSV2DCC User Guide" for instructions on how to create a Parameterised version of the Input DCC, and how to create the CSV (semicolon separated) file which will contain the metrology information to be embedded into the DCC.

The working pre-release version section of this site contains information on how to set CSV2DCC up on a windows PC running JRE 1.8 or later.

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



