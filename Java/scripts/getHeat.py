import mysql.connector
from sense_hat import SenseHat

mydb = mysql.connector.connect(
              host="localhost",
              user="admin",
              password="admin",
              database="sensors"
            )



sense = SenseHat()


mycursor = mydb.cursor()

mycursor.execute("SELECT * FROM `meting` A LEFT JOIN meting_types B ON A.metingTypesID = B.TypeID WHERE `timestamp` in (SELECT max(`timestamp`) FROM `meting` GROUP BY `metingTypesID` );")

myresult = mycursor.fetchall()

for result in myresult:
	sense.show_message("meting type " + result[4] + " is " + result[0], text_colour=[255, 0, 255])