# Aufgabe 1
![image](https://github.com/user-attachments/assets/7f1aea05-0772-435e-930d-91081b634eb8)
- Ich verwende Postman, um die REST-Schnittstelle des Backends zu automatisieren und zu testen.

## Aufgabe 2

![cypress](https://github.com/user-attachments/assets/27fe1176-1e51-4aee-b0fc-e395e4f9e001)
![cypress](https://github.com/user-attachments/assets/9867277a-2bf0-4306-911a-4c4f2329f6bf)

### Tool
Für die End-To-End-Tests verwende ich **Cypress**.

### Vorgehen
- Cypress wurde ins Projekt integriert.
- Zwei Testfälle wurden erstellt:
  1. **"See All Users"**: Überprüfung der Nutzerliste.
  2. **"Add a new Student"**: Automatisiertes Hinzufügen eines neuen Studenten.

### Ergebnis
Die Tests wurden erfolgreich in einem Browser ausgeführt und das gewünschte Verhalten der Applikation konnte validiert werden.

---

## Aufgabe 3

### Ziel
Das Ziel war es, das Backend durch erhöhten Traffic zu belasten und die Funktionalitäten des Tools Postman zu erkunden. Zwei wichtige Endpunkte der Angular-Applikation wurden getestet:
- **POST /addstudents**: Fügt einen neuen Studenten hinzu.
- **GET /students**: Ruft die Liste aller Studenten ab.

### Vorgehen

1. **Erstellung einer Postman-Collection**:
   - Eine neue Postman-Collection wurde erstellt, um die beiden Endpunkte systematisch zu testen.
   - **POST /addstudents**: Dieser Endpunkt wurde für das Hinzufügen neuer Studenten getestet. Die Daten wurden in einem JSON-Format gesendet:
     ```json
     {
       "name": "Max Mustermann",
       "email": "max.mustermann@example.com"
     }
     ```
   - **GET /students**: Dieser Endpunkt wurde aufgerufen, um die vollständige Liste der hinzugefügten Studenten abzurufen.

2. **Konfiguration von Testläufen**:
   - Mithilfe der **Collection Runner**-Funktion in Postman wurde der Test automatisiert und mehrfach ausgeführt, um den Endpunkt mit hohem Traffic zu belasten.
   - Iterationen: 1000
   - Verzögerung zwischen den Anfragen: 0 ms

3. **Funktionalitäten erkundet**:
   - **Testscripting**: Mit Postman wurden Testskripte hinzugefügt, um den Statuscode der Antworten zu überprüfen:
     ```javascript
     pm.test("Status code is 200", function () {
         pm.response.to.have.status(200);
     });
     ```
   - **Datenvalidierung**: Es wurde geprüft, ob die hinzugefügten Studenten korrekt in der Liste erscheinen.

4. **Beobachtungen**:
   - **POST /addstudents**: Alle Anfragen wurden erfolgreich verarbeitet, und neue Studenten wurden hinzugefügt.
   - **GET /students**: Die Abfragen haben zuverlässig die vollständige Liste zurückgegeben, inklusive der neu hinzugefügten Studenten.
   - Das Backend konnte den Traffic gut verarbeiten, es gab keine Timeout-Fehler oder Abstürze.
