Open the BloodBankAppFinal File in Android Studio.

1. Please note that the JSON file linking to be done for displaying the last and the next 
donation date has to be done on the OptionActivity.java Activity, Replacing the URL variable value,
to whatever the real Database is.

The Format of the JSON File has to be as follows:

[{"phone_number":"+91XXXXXXXXXX","last_donation_date":"DD-MM-YYYY","next_donation_date":"DD-MM-YYYY"}]


2. The second JSON linking has to be done for getting the Blood Unit count. 
The Format of this file has to be as follows. Replacing the URL variable value in the BloodCount.java,
to whatever the real Database is.:

[{"blood_group":"A+","component_name":"Packed Red Blood Cells","units_available":"24"}]

3. Comments have been made in every JAVA file to make their functions clearer.

4. The Login information for accessing the Firebase WebAdmin are:
				
			Username: ircsbloodbank100@gmail.com
			Password: redcrosssociet
