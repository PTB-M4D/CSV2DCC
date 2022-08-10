# CSV2DCC 

The purpose of the CSV2DCC application is to embed metrology data held within a CSV (actually a semicolon separated) file into a Digital Calibration Certifcate (DCC) XML file. It is expected that this application will be of use in the generation of DCCs in the production environment.

To use CSV2DCC, the User will first need to prepare a DCC that is suitable for their work, this will be the "Input DCC" for the CSV2DCC application.

(The Input DCC will probably contain dummy, typical data, but this is not absolutely necessary)

To generate the Input DCC, please refer to the core DCC documentation at https://www.ptb.de/dcc/ for full details on how to create a DCC suitable for a particular case. Please also refer to the GEMIMEG Tool which provides an easy way to generate a DCC XML file using a GUI for a number of fields of metrology including Temperature and Humidity.

Once the Input DCC has been generated, please refer to the "CSV2DCC User Guide" for instructions on how to create a Parameterised version of the Input DCC, and how to create the CSV (semicolon separated) file which will contain the metrology information to be embedded into the DCC.

The working pre-release version section of this site contains information on how to set CSV2DCC up on a windows PC running JRE 1.8 or later.

# CSV2DCC User Guide
