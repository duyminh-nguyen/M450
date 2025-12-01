# Test-Levels
## Aufgabe 1

### Was wird wie in Ihrer Firma getestet? Cognizant
- **Was:** Funktionalität, Performance, Sicherheit, Usability, Kompatibilität.
- **Wie:** Manuell und automatisiert (Unit-, Integration-, Systemtests).

### Mit welchen Test Levels hatten Sie bereits zu tun?
- Unit, Integration, System, Acceptance.

### Wann werden Tests ausgeführt?
- Während der Entwicklung (CI/CD), vor Releases, nach Updates.

### Haben Sie dedizierte Testing- oder QA-Teams?
- Nein.

### Wie sieht Ihr Testing Life Cycle aus?
1. Planung: Anforderungen analysieren, Teststrategie erstellen.
2. Design: Testfälle schreiben, Testumgebungen vorbereiten.
3. Implementierung: Tests entwickeln (manuell/automatisiert).
4. Ausführung: Tests durchführen, Ergebnisse dokumentieren.
5. Reporting: Fehler analysieren und dokumentieren.
6. Wartung: Tests anpassen, Regressionstests ausführen.

## Aufgabe 2

### Begriffe und Abhängigkeiten

#### Testing Approach
- Strategien, z. B. Risk-Based Testing, Exploratory Testing.

#### Testing Levels
- Hierarchien: Unit, Integration, System, Acceptance.

#### Testing Types, Techniques und Tactics
- **Types:** Funktional (z. B. Regression), nicht-funktional (z. B. Performance).
- **Techniques:** Black-Box, White-Box, Grey-Box.
- **Tactics:** Automatisierung, exploratives Testen.

---

# Unit-Testing

## Aufgabe 1
### Unsere Tests:
![image](https://github.com/user-attachments/assets/cf5bea17-4436-4b46-b65e-062c58b42dc9)

### Testen in Entwicklungsumgebung
![image](https://github.com/user-attachments/assets/6725ebeb-a3d8-454d-92a3-78d0c0123a60)

### Maven Test:
![image](https://github.com/user-attachments/assets/30abc02e-575a-4366-a268-896845241508)

## Aufgabe 2

## 1. **Test-Annotationen**

### **@Test**
Markiert eine Methode als Testmethode.

**Anwendungsfall:**
```java
@Test
public void testAdd() {
    Main main = new Main();
    assertEquals(5.0f, main.add(2.0f, 3.0f));
}
```

### **@BeforeEach**
Wird vor jeder Testmethode ausgeführt. Ideal für die Initialisierung von Objekten.

**Anwendungsfall:**
```java
@BeforeEach
public void setUp() {
    main = new Main();
}
```

### **@AfterEach**
Wird nach jeder Testmethode ausgeführt. Nützlich zum Bereinigen von Ressourcen.

**Anwendungsfall:**
```java
@AfterEach
public void tearDown() {
    calc = null;
}
```

### **@BeforeAll**
Wird einmal vor allen Testmethoden ausgeführt. Muss in einer statischen Methode verwendet werden.

**Anwendungsfall:**
```java
@BeforeAll
public static void initialize() {
    database.connect();
}
```

### **@AfterAll**
Wird einmal nach allen Testmethoden ausgeführt. Muss ebenfalls statisch sein.

**Anwendungsfall:**
```java
@AfterAll
public static void cleanUp() {
    database.disconnect();
}
```

### **@Disabled**
Deaktiviert einen Test oder eine Testklasse.

**Anwendungsfall:**
```java
@Disabled("Feature in Entwicklung")
@Test
public void testNewFeature() {
    // Test wird übersprungen
}
```

---

## **Assertions**
Assertions dienen zur Überprüfung der erwarteten Ergebnisse.

### **assertEquals**
Vergleicht zwei Werte auf Gleichheit.

**Anwendungsfall:**
```java
assertEquals(5, main.add(2, 3));
```

### **assertNotEquals**
Überprüft, ob zwei Werte unterschiedlich sind.

**Anwendungsfall:**
```java
assertNotEquals(4, main.subtract(5, 1));
```

### **assertTrue / assertFalse**
Überprüft, ob ein Ausdruck wahr oder falsch ist.

**Anwendungsfall:**
```java
assertTrue(list.isEmpty());
assertFalse(list.contains("Element"));
```

### **assertThrows**
Überprüft, ob eine bestimmte Ausnahme geworfen wird.

**Anwendungsfall:**
```java
assertThrows(ArithmeticException.class, () -> main.divide(1, 0));
```

### **assertAll**
Gruppiert mehrere Assertions, die unabhängig voneinander geprüft werden.

**Anwendungsfall:**
```java
assertAll(
    () -> assertEquals(4, main.add(2, 2)),
    () -> assertEquals(0, main.subtract(2, 2))
);
```

---

## **Test-Lifecycle-Methoden**
JUnit bietet Methoden, um den Testzyklus zu steuern:

- **@BeforeEach** / **@AfterEach**: Für Setup und Cleanup pro Test.
- **@BeforeAll** / **@AfterAll**: Für einmalige Initialisierung und Aufräumen.

### **Anwendungsfall:**
```java
@BeforeEach
public void setUp() {
    database.connect();
}

@AfterEach
public void tearDown() {
    database.disconnect();
}
```

---

## **Parameterized Tests**
JUnit ermöglicht das Durchführen von Tests mit unterschiedlichen Eingabewerten.

### **@ParameterizedTest**
Markiert einen Test als parametrisiert.

### **@ValueSource**
Gibt eine Liste von Eingabewerten an.

**Anwendungsfall:**
```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 4, 5})
public void testIsOdd(int number) {
    assertTrue(number % 2 != 0);
}
```

### **@CsvSource**
Erlaubt die Übergabe von mehreren Werten pro Testlauf.

**Anwendungsfall:**
```java
@ParameterizedTest
@CsvSource({"1, true", "2, false"})
public void testIsOdd(int number, boolean isOdd) {
    assertEquals(isOdd, number % 2 != 0);
}
```

---

## **Timeouts**
JUnit unterstützt Zeitlimits für Tests, um lange Ausführungen zu verhindern.

### **@Timeout**
Legt ein Zeitlimit für den Test fest.

**Anwendungsfall:**
```java
@Test
@Timeout(1) // Test muss innerhalb einer Sekunde abgeschlossen sein
public void testPerformance() {
    Thread.sleep(500);
}
```

---

### Verlinkung einer Referenzseite
Für weitere Details und Beispiele besuchen Sie die [offizielle JUnit-Dokumentation](https://junit.org/junit5/docs/current/user-guide/).

---

# Übung 3: Banken Simulation

## Funktionsweise der Software

### **Hauptklassen**

#### **<ins>Account</ins>**

Hier werden die grundlegende Eigenschaften und Methoden eines Kontos definiert:

- Attribute: ```id, balance```, ```bookings```
- Methoden: ```deposit(int date, long amount)```, ```withdraw(int date, long amount)```, ```getId()```, ```getBalance()```, ```print()```  
  Einzahlen und Zurückziehen vom Geld; Kontostand und Kontonummer bekommen.

---

#### **<ins>SavingsAccount</ins>** (erbt von Account)

Spezialisierte Kontoart für gespartes Guthaben:

- Methoden: ```withdraw(int date, long amount)```  
  Guthaben prüfen, ruft Account.java wenn genügend Guthaben vorhanden, verhindert Abhebungen wenn nicht.

---

#### **<ins>SalaryAccount</ins>** (erbt von Account)

Konto für Gehaltseinzahlungen:

- Attribute: ```creditLimit``` → Kreditlimit des Kontos, erlaubt Überziehungen bis zum definierten negativen Betrag.  
- Methoden: ```withdraw(int date, long amount)```  
  Berechnet Guthaben nach Abhebung, verhindert falls Limit überschritten wird; ruft Account.java, wenn Bedingung erfüllt.

---

#### **<ins>PromoYouthSavingsAccount</ins>** (erbt von SavingsAccount)

Jugend-Sparkonto mit Promotionen:

- Methoden: ```deposit(int date, long amount)```  
  Bei Einzahlungen wird ein Bonus von 1% des Betrags gewährt.

---

#### **<ins>Bank</ins>**

Verwaltung einer Sammlung von Konten:

- Attribute: ```accounts``` (verwaltet Konten), ```nextAccountId``` (automatisch generierte Id für neue Konten)
- Methoden:
  - Kontoerstellung:  
    ```createSavingsAccount()```, ```createPromoYouthAccount()```, ```createSalaryAccount(long creditLimit)```  
    Erstellt verschiedene Kontotypen.
  - Transaktionen:  
    ```deposit(String id, int date, long amount)```, ```withdraw(String id, int date, long amount)```  
    Führt Einzahlungen und Abhebungen auf einem bestimmten Konto aus.
  - Saldo-Abfragen:  
    ```getBalance()```, ```getBalance(String id)```  
    Gibt Gesamtsaldo oder spezifischen Kontostand zurück.
  - Kontodruck:  
    ```print(String id)```, ```print(String id, int year, int month)```, ```printTop5()```, ```printBottom5()```  
    Gibt Kontoauszüge aus oder zeigt die fünf Konten mit dem höchsten/niedrigsten Saldo.

---

#### **<ins>Booking</ins>**

Repräsentiert eine Buchung:

- Attribute: ```date```, ```amount```  
- Methoden: ```Booking(int date, long amount)```, ```getDate()```, ```getAmount()```, ```print(long balance)```  
  Erstellt die Buchung, gibt Datum/Betrag zurück, druckt eine Buchungszeile mit aktuellem Saldo.

---

#### **<ins>BankUtils</ins>**

Dienstprogrammklasse mit hilfreichen Funktionen:

- Attribute: ```TWO_DIGIT_FORMAT```, ```AMOUNT_FORMAT```
- Methoden: ```formatBankDate(int date)```, ```formatAccount(long amount)```  
  Formatiert Bankdaten und Beträge.

---

## **Beziehungen zwischen den Klassen**

- **Bank → Account**  
  - Die Bank **erstellt Konten** und speichert sie in ```accounts```.  
  - Die Bank **ruft Methoden der Accounts auf**, z. B. ```deposit()```, ```withdraw()```, ```print()```.

- **Account → Booking**  
  - Jeder Account **erstellt Booking-Objekte**, wenn Einzahlungen oder Abhebungen stattfinden.  
  - Die Buchungen werden in ```bookings``` gespeichert (Komposition: ohne Account existiert Booking nicht).

- **Booking → BankUtils**  
  - Booking nutzt BankUtils für die **Formatierung von Datum und Beträgen**.

- **Vererbungshierarchie (Account als Basisklasse)**  
  - ```SavingsAccount```, ```SalaryAccount``` und ```PromoYouthSavingsAccount``` **erweitern Account**.  
  - Sie **überschreiben Methoden**, um spezielles Verhalten (Limit, Bonus, Sperrlogik) umzusetzen.

- **SavingsAccount → PromoYouthSavingsAccount**  
  - PromoYouthSavingsAccount erweitert SavingsAccount und erbt dessen Auszahlungslogik.

---

## **Ablauf einer typischen Transaktion**

1. Die Bank erhält einen Aufruf wie ```deposit(id, date, amount)```.  
2. Die Bank sucht das Konto in ```accounts```.  
3. Das Konto führt ```deposit()``` aus.  
4. Ein neues ```Booking``` wird erstellt und gespeichert.  
5. Der Saldo wird aktualisiert.  
6. Die Ausgabe erfolgt über ```print()``` mit Unterstützung von ```BankUtils```.


