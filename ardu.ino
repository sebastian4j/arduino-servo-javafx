#include <Servo.h>

Servo servo;

void setup() {
  servo.attach(9);
  servo.write(0);
  Serial.begin(9600);
}

void loop() {
  int leido = Serial.read();
  Serial.println(leido);
  if (leido == 1) {
    servo.write(120);
  } else if (leido == 2) {
    servo.write(20);
  }   
  
  delay(100);
}
