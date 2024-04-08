#define TRIG_PIN 9
#define ECHO_PIN 8

void setup() {
  Serial.begin(9600);
  pinMode(TRIG_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);  
}

void loop() {
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);
  
  long distance = pulseIn(ECHO_PIN, HIGH) / 58;
  
  Serial.println(distance);
  delay(1000);
}
