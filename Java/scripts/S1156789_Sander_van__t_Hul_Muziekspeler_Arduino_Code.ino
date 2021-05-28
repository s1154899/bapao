//define drukknoppen, ldr en piezospeaker
#define buttonLeft 3
#define buttonRight 2
#define ldr A0
#define buzzer 13
#define potentio A5

//define 7 segment display en leds
#define maxDigit 7
#define redLed 11
#define greenLed 12

//define muzieknoten
#define NOTE_B0  31
#define NOTE_C1  33
#define NOTE_CS1 35
#define NOTE_D1  37
#define NOTE_DS1 39
#define NOTE_E1  41
#define NOTE_F1  44
#define NOTE_FS1 46
#define NOTE_G1  49
#define NOTE_GS1 52
#define NOTE_A1  55
#define NOTE_AS1 58
#define NOTE_B1  62
#define NOTE_C2  65
#define NOTE_CS2 69
#define NOTE_D2  73
#define NOTE_DS2 78
#define NOTE_E2  82
#define NOTE_F2  87
#define NOTE_FS2 93
#define NOTE_G2  98
#define NOTE_GS2 104
#define NOTE_A2  110
#define NOTE_AS2 117
#define NOTE_B2  123
#define NOTE_C3  131
#define NOTE_CS3 139
#define NOTE_D3  147
#define NOTE_DS3 156
#define NOTE_E3  165
#define NOTE_F3  175
#define NOTE_FS3 185
#define NOTE_G3  196
#define NOTE_GS3 208
#define NOTE_A3  220
#define NOTE_AS3 233
#define NOTE_B3  247
#define NOTE_C4  262
#define NOTE_CS4 277
#define NOTE_D4  294
#define NOTE_DS4 311
#define NOTE_E4  330
#define NOTE_F4  349
#define NOTE_FS4 370
#define NOTE_G4  392
#define NOTE_GS4 415
#define NOTE_A4  440
#define NOTE_AS4 466
#define NOTE_B4  494
#define NOTE_C5  523
#define NOTE_CS5 554
#define NOTE_D5  587
#define NOTE_DS5 622
#define NOTE_E5  659
#define NOTE_F5  698
#define NOTE_FS5 740
#define NOTE_G5  784
#define NOTE_GS5 831
#define NOTE_A5  880
#define NOTE_AS5 932
#define NOTE_B5  988
#define NOTE_C6  1047
#define NOTE_CS6 1109
#define NOTE_D6  1175
#define NOTE_DS6 1245
#define NOTE_E6  1319
#define NOTE_F6  1397
#define NOTE_FS6 1480
#define NOTE_G6  1568
#define NOTE_GS6 1661
#define NOTE_A6  1760
#define NOTE_AS6 1865
#define NOTE_B6  1976
#define NOTE_C7  2093
#define NOTE_CS7 2217
#define NOTE_D7  2349
#define NOTE_DS7 2489
#define NOTE_E7  2637
#define NOTE_F7  2794
#define NOTE_FS7 2960
#define NOTE_G7  3136
#define NOTE_GS7 3322
#define NOTE_A7  3520
#define NOTE_AS7 3729
#define NOTE_B7  3951
#define NOTE_C8  4186
#define NOTE_CS8 4435
#define NOTE_D8  4699
#define NOTE_DS8 4978
#define REST      0

/*-------------------------
Variabelen voor algortime
-------------------------*/
unsigned long currentTime;
unsigned long previousTime;

//deze boolean houdt bij of de noot is afgelopen
bool noteEnded = false; 

//declareer waarde van de knop
bool leftButton;
bool rightButton;
bool leftButtonPressed = false;
bool rightButtonPressed = false;

//break uit de loop als de song verandert
bool breakLoop = false;

//zet de song op 1
int currentSong = 1;

//deze variabel houdt de waarde van de ldr bij
int ldrValue;

//zet de display nummers in array
int display[][maxDigit] =
{
  {LOW,HIGH,HIGH,LOW,LOW,LOW,LOW},// getal 1 op plekje 0
  {HIGH,HIGH,LOW,HIGH,HIGH,LOW,HIGH},//getal 2 op plekje 1 etc.
  {HIGH,HIGH,HIGH,HIGH,LOW,LOW,HIGH},//3
  {LOW,HIGH,HIGH,LOW,LOW,HIGH,HIGH},//4
  {HIGH,LOW,HIGH,HIGH,LOW,HIGH,HIGH},//5
  {HIGH,LOW,HIGH,HIGH,HIGH,HIGH,HIGH},//6
  {HIGH,HIGH,HIGH,LOW,LOW,LOW,LOW},//7
  
};
int outputs[7] = {8, 7, 4, 5, 6, 10, 9}; //array voor de output

/*--------------------------
Variabelen voor alle liedjes
--------------------------*/

int divider = 0, noteDuration = 0;  //variabelen die de liedjes nodig hebben
int startTempos[7] = {120, 105, 140, 108, 144, 144, 200};//starttempo voor de liedjes
int startTempo;
int tempo;

//multidimensional array met alle liedjes erin 
int melodys[][98]
{
  {
  // Imperial march
  NOTE_A4,-4, NOTE_A4,-4, NOTE_A4,16, NOTE_A4,16, NOTE_A4,16, NOTE_A4,16, NOTE_F4,8, REST,8,
  NOTE_A4,-4, NOTE_A4,-4, NOTE_A4,16, NOTE_A4,16, NOTE_A4,16, NOTE_A4,16, NOTE_F4,8, REST,8,
  NOTE_A4,4, NOTE_A4,4, NOTE_A4,4, NOTE_F4,-8, NOTE_C5,16,

  NOTE_A4,4, NOTE_F4,-8, NOTE_C5,16, NOTE_A4,2,
  NOTE_E5,4, NOTE_E5,4, NOTE_E5,4, NOTE_F5,-8, NOTE_C5,16,
  NOTE_A4,4, NOTE_F4,-8, NOTE_C5,16, NOTE_A4,2,
  
  NOTE_A5,4, NOTE_A4,-8, NOTE_A4,16, NOTE_A5,4, NOTE_GS5,-8, NOTE_G5,16,
  NOTE_DS5,16, NOTE_D5,16, NOTE_DS5,8, REST,8, NOTE_A4,8, NOTE_DS5,4, NOTE_D5,-8, NOTE_CS5,16, REST,4,
 },
 {
  // Pacman
  NOTE_B4,16, NOTE_B5,16, NOTE_FS5,16, NOTE_DS5,16,
  NOTE_B5,32, NOTE_FS5,-16, NOTE_DS5,8, NOTE_C5,16,
  NOTE_C6,16, NOTE_G6,16, NOTE_E6,16, NOTE_C6,32, NOTE_G6,-16, NOTE_E6,8,

  NOTE_B4,16,  NOTE_B5,16,  NOTE_FS5,16,   NOTE_DS5,16,  NOTE_B5,32,
  NOTE_FS5,-16, NOTE_DS5,8,  NOTE_DS5,32, NOTE_E5,32,  NOTE_F5,32,
  NOTE_F5,32,  NOTE_FS5,32,  NOTE_G5,32,  NOTE_G5,32, NOTE_GS5,32,  NOTE_A5,16, NOTE_B5,8,

  NOTE_B4,16,  NOTE_B5,16,  NOTE_FS5,16,   NOTE_DS5,16,  NOTE_B5,32,
  NOTE_FS5,-16, NOTE_DS5,8,  NOTE_DS5,32, NOTE_E5,32,  NOTE_F5,32,
  NOTE_F5,32,  NOTE_FS5,32,  NOTE_G5,32,  NOTE_G5,32, NOTE_GS5,32,  NOTE_A5,16, NOTE_B5,8, REST,4,
 },
 {
  // Green Hill Zone - Sonic the Hedgehog
  REST,2, NOTE_D5,8, NOTE_B4,4, NOTE_D5,8,
  NOTE_CS5,4, NOTE_D5,8, NOTE_CS5,4, NOTE_A4,2, 
  REST,8, NOTE_A4,8, NOTE_FS5,8, NOTE_E5,4, NOTE_D5,8,
  NOTE_CS5,4, NOTE_D5,8, NOTE_CS5,4, NOTE_A4,2, 
  REST,4, NOTE_D5,8, NOTE_B4,4, NOTE_D5,8,
  NOTE_CS5,4, NOTE_D5,8, NOTE_CS5,4, NOTE_A4,2, 

  REST,8, NOTE_B4,8, NOTE_B4,8, NOTE_G4,4, NOTE_B4,8,
  NOTE_A4,4, NOTE_B4,8, NOTE_A4,4, NOTE_D4,2,
  REST,4, NOTE_D5,8, NOTE_B4,4, NOTE_D5,8,
  NOTE_CS5,4, NOTE_D5,8, NOTE_CS5,4, NOTE_A4,2, 
  REST,8, NOTE_A4,8, NOTE_FS5,8, NOTE_E5,4, NOTE_D5,8,
  NOTE_CS5,4, REST,4,
 },
 {
  // Zelda's Lullaby - The Legend of Zelda Ocarina of Time.
  NOTE_E4,2, NOTE_G4,4,
  NOTE_D4,2, NOTE_C4,8, NOTE_D4,8, 
  NOTE_E4,2, NOTE_G4,4,
  NOTE_D4,-2,
  NOTE_E4,2, NOTE_G4,4,
  NOTE_D5,2, NOTE_C5,4,
  NOTE_G4,2, NOTE_F4,8, NOTE_E4,8, 
  NOTE_D4,-2,
  NOTE_E4,2, NOTE_G4,4,
  NOTE_D4,2, NOTE_C4,8, NOTE_D4,8, 
  NOTE_E4,2, NOTE_G4,4,
  NOTE_D4,-2,
  NOTE_E4,2, NOTE_G4,4,

  NOTE_D5,2, NOTE_C5,4,
  NOTE_G4,2, NOTE_F4,8, NOTE_E4,8, 
  NOTE_F4,8, NOTE_E4,8, NOTE_C4,2,
  NOTE_F4,2, NOTE_E4,8, NOTE_D4,8, 
  NOTE_E4,8, NOTE_D4,8, NOTE_A3,2,
  NOTE_G4,2, NOTE_F4,8, NOTE_E4,8, 
  NOTE_F4,8, NOTE_E4,8, NOTE_C4,4, NOTE_F4,4,
  NOTE_C5,-2, REST,4,
 },
 {
  // Harry Potter
  REST,2, NOTE_D4,4,
  NOTE_G4,-4, NOTE_AS4,8, NOTE_A4,4,
  NOTE_G4,2, NOTE_D5,4,
  NOTE_C5,-2, 
  NOTE_A4,-2,
  NOTE_G4,-4, NOTE_AS4,8, NOTE_A4,4,
  NOTE_F4,2, NOTE_GS4,4,
  NOTE_D4,-1, 
  NOTE_D4,4,

  NOTE_G4,-4, NOTE_AS4,8, NOTE_A4,4,
  NOTE_G4,2, NOTE_D5,4,
  NOTE_F5,2, NOTE_E5,4,
  NOTE_DS5,2, NOTE_B4,4,
  NOTE_DS5,-4, NOTE_D5,8, NOTE_CS5,4,
  NOTE_CS4,2, NOTE_B4,4,
  NOTE_G4,-1,
  NOTE_AS4,4,
     
  NOTE_D5,2, NOTE_AS4,4,
  NOTE_D5,2, NOTE_AS4,4,
  NOTE_DS5,2, NOTE_D5,4,
  NOTE_CS5,2, NOTE_A4,4,
  NOTE_AS4,-4, NOTE_D5,8, NOTE_CS5,4,
  NOTE_CS4,2, NOTE_D4,4,
  NOTE_D5,-1, 
  REST,4, NOTE_AS4,4, REST,4,  
 },
 {
  //tetris
  NOTE_E5, 4,  NOTE_B4,8,  NOTE_C5,8,  NOTE_D5,4,  NOTE_C5,8,  NOTE_B4,8,
  NOTE_A4, 4,  NOTE_A4,8,  NOTE_C5,8,  NOTE_E5,4,  NOTE_D5,8,  NOTE_C5,8,
  NOTE_B4, -4,  NOTE_C5,8,  NOTE_D5,4,  NOTE_E5,4,
  NOTE_C5, 4,  NOTE_A4,4,  NOTE_A4,4,  REST, 4,  REST, 8, 
  NOTE_B4,8,  NOTE_C5,8,

  NOTE_D5, -4,  NOTE_F5,8,  NOTE_A5,4,  NOTE_G5,8,  NOTE_F5,8,
  NOTE_E5, -4,  NOTE_C5,8,  NOTE_E5,4,  NOTE_D5,8,  NOTE_C5,8,
  NOTE_B4, 4,  NOTE_B4,8,  NOTE_C5,8,  NOTE_D5,4,  NOTE_E5,4,
  NOTE_C5, 4,  NOTE_A4,4,  NOTE_A4,4, REST, 16,

  REST, 16,  REST, 16,  REST, 16,  REST, 16,//korte rusten toegevoegd zodat alle 
  REST, 16,  REST, 16,  REST, 16,           //plaatsen in de array zijn gevuld
 },
 {
  //Super Mario
  NOTE_E5,8, NOTE_E5,8, REST,8, NOTE_E5,8, REST,8, NOTE_C5,8, NOTE_E5,8,
  NOTE_G5,4, REST,4, NOTE_G4,8, REST,4, 
  NOTE_C5,-4, NOTE_G4,8, REST,4, NOTE_E4,-4,
  NOTE_A4,4, NOTE_B4,4, NOTE_AS4,8, NOTE_A4,4,
  NOTE_G4,-8, NOTE_E5,-8, NOTE_G5,-8, NOTE_A5,4, NOTE_F5,8, NOTE_G5,8,
  REST,8, NOTE_E5,4,NOTE_C5,8, NOTE_D5,8, NOTE_B4,-4,
  NOTE_C5,-4, NOTE_G4,8, REST,4, NOTE_E4,-4,
  NOTE_A4,4, NOTE_B4,4, NOTE_AS4,8, NOTE_A4,4,
  NOTE_G4,-8, NOTE_E5,-8, NOTE_G5,-8, NOTE_A5,4, NOTE_F5,8, NOTE_G5,8,
  REST,8, NOTE_E5,4,NOTE_C5,8, NOTE_D5,8, NOTE_B4,-4,
 }
};


/*-------------
     Setup
--------------*/

void setup()
{
  pinMode(buttonLeft, INPUT); //zet linkerknop op input
  pinMode(buttonRight, INPUT); //zet rechterknop op input
  
  pinMode(ldr, INPUT);//zet ldr op input
  pinMode(potentio, INPUT); //zet de potentiometer op input
  
  pinMode(redLed, OUTPUT); //zet rode led op output
  pinMode(greenLed, OUTPUT); //zet green led op output
  
  for(int i = 4; i <= 10; i++) //zet de 7 segment display op OUTPUT
  {
    pinMode(i, OUTPUT);
  }
  pinMode(buzzer, OUTPUT);
  previousTime = millis(); //zet previoustime op 0
  Serial.begin(9600); //start serial connectie
}

/*-------------
     Loop
-------------*/
void loop()
{
  delay(20); //voor stabiliteit
  ldrValue = analogRead(ldr); //lees de ldr af om te kijken of die boven 100 komt
  
  if(ldrValue > 200) //komt de ldrwaarde boven 200 gaat deze code aan het werk
  {
    greenOn();//doet het groenje ledje aan en de rode uit
    delay(20);//stabiliteit
    checkForInput();  //check of er op de knoppen wordt gedrukt
    if(currentSong == 1){playSong(0);} //als currentsong 1 is speel imperialmarch
    else if(currentSong == 2){playSong(1);} //als currentsong 2 is speel pacman enz.
    else if(currentSong == 3){playSong(2);}
    else if(currentSong == 4){playSong(3);}
    else if(currentSong == 5){playSong(4);} 
    else if(currentSong == 6){playSong(5);} 
    else if(currentSong == 7){playSong(6);} 
    changeSong();
  }
  else{ redOn(); } //als ldrvalue lager is dan hondert moet de rode ledje aan
   
}

/*-------------
    Functies
--------------*/

void checkForInput() //deze functie kijkt of er input van de gebruiker is. 
{                    //hij checkt bijvoorbeeld of er input is van de knoppen en de ldr
  delay(20);
  leftButton = digitalRead(buttonLeft); //check de linkerknop
  rightButton = digitalRead(buttonRight); //check de rechterknop
  ldrValue = analogRead(ldr); //check de ldr
  
  if(leftButton)
  {
    noTone(buzzer);
    delay(750);
    leftButtonPressed = true;
    breakLoop = true;
  }

  if(rightButton)
  {
    noTone(buzzer);
    delay(750);
    rightButtonPressed = true;
    breakLoop = true;
  }
}

void changeSong()//verander het liedje gebaseert op de knop die wordt ingedrukt.
{
  if(leftButtonPressed) //check of links was ingedrukt
  {
    leftButtonPressed = false;
    if(currentSong != 1)//als het liedje al 1 is, dan hoeft hij niet 1 terug
    {
      currentSong--;
    }
  }  

  if(rightButtonPressed) // check of rechts was ingedrukt
  {
    rightButtonPressed = false;
    if(currentSong != 7)//als het liedje al 5 is, dan hoeft hij niet 1 verder
    {
      currentSong++;
    }
  } 
}

void redOn()//rode ledje aan, groene uit
{
  digitalWrite(redLed, HIGH);
  digitalWrite(greenLed, LOW);
}

void greenOn()//groene ledje aan, rode uit
{
  digitalWrite(redLed, LOW);
  digitalWrite(greenLed, HIGH);
}

void showSongDigit()//handelt de 7segment display af
{
    for(int i=0; i<7; i++)
    {
      digitalWrite(outputs[i], display[currentSong-1][i]);
    }
}

void off() //als de ldrvalue < 200 wordt deze functie uitgevoerd
{
  while(ldrValue < 200)//zolang ldrvalue < 200 doe het volgende:
  {
    noTone(buzzer); //piezo uit
    delay(20);
    redOn();   //roodje ledje aan, groene uit
    for(int i=0; i<7; i++)   //7 segment display uit
    {
      digitalWrite(outputs[i], LOW);
    }
    ldrValue = analogRead(ldr); //lees de ldr af om te kijken of die weer boven 100 komt
  }
}

void getStartTempo()//deze functie pakt het juiste starttempo
{
  for(int i=0; i<=sizeof(startTempos)/sizeof(startTempos[0]); i++)
  {
    if(currentSong == i)//als het liedje overeenkomt met de i in de functie dan voert
    {                   //hij de volgende code uit
      startTempo = startTempos[i - 1]; //hier wordt het juiste tempo uit de array gehaalt
      Serial.println(startTempo);
    }
  }
}

void changeTempo()//deze functie veranderd het tempo
{
  int potentioValue = analogRead(potentio); //leest de waarde van de potentiometer af
  getStartTempo();//haalt het starttempo van elk liedje op
  tempo = (potentioValue / 5) + startTempo; //deelt de waarde van de potentio door 5 om te voorkomen
}                                           //dat het liedje anders veel te snel afspeelt

void checkIfNoteEnded()//deze functie controleert of de noteduration afgelopen is
{
  if(currentTime - previousTime >= noteDuration)//als de noot duration afgelopen is 
  {                                             //wordt de volgende code uitgevoerd
    noTone(buzzer);
    previousTime = currentTime;
    noteEnded = true;
  }
}

void whileNotePlays()//deze functie houdt in de gaten wat er allemaal moet 
{                    //gebeuren als er een noot speelt
  while(noteEnded != true)//dit blokje checkt of de noot afgelopen is en wat er 
  {                       //ondertussen moet gebeuren
    delay(20);
    checkForInput();//check voor input (knoppen en ldr)
    if(breakLoop){break;} //break de loop als er op knoppen wordt gedrukt
    off(); //stopt met alles als ldr < 200
    greenOn(); //groene ledje aan, rode uit
    currentTime = millis();
    checkIfNoteEnded();
  }
}

void playSong(int song) {
  //hoewel het zo is dat elk liedje hetzelfde aantal noten heeft, kan 'notes' niet gelijk worden gestelt aan 98
  //als dat wel wordt gedaan, dan gaat de forloop verder met de volgende array van de melodys array
  //om dit te voorkomen gebruik ik sizeof.
  int notes = sizeof(melodys[song]) / sizeof(melodys[song][0]) / 2;

  for (int thisNote = 0; thisNote < notes * 2; thisNote = thisNote + 2) {
    
   showSongDigit();//laat nummer van liedje zien
   
    changeTempo();//verandert tempo van liedje
    int wholenote = (60000 * 4) / tempo;
    delay(20);
    divider = melodys[song][thisNote + 1];//dit blokje handelt de tijd van de noot af
      if (divider > 0) 
      {
        noteDuration = (wholenote) / divider;
      } 
      else if (divider < 0) 
      {
        noteDuration = (wholenote) / abs(divider);
        noteDuration *= 1.5; 
      }

    tone(buzzer, melodys[song][thisNote], noteDuration*0.9);
    
    whileNotePlays();//deze houdt functies bij die moeten worden uitgevoerd 
                     //speelt
     noteEnded = false;
   if(breakLoop){break;}
  }
 if(breakLoop){breakLoop = false;}
}
