#include <Servo.h>

Servo servo;
Servo servo2;

void setup() {
  servo.attach(9);
  servo.write(0);
  servo2.attach(10);
  servo2.write(0);
  Serial.begin(9600);
}

void loop() {
  int leido = Serial.read();
  Serial.println(leido);
  if (leido == 1) {
    servo.write(120);
    servo2.write(40);
  } else if (leido == 2) {
    servo.write(40);
    servo2.write(120);
  }   
  
  delay(100);
}
