# Aufgaben

## Aufgabe 1: Formen von Tests in der Informatik

1. **Komponententest (Unit Test):**  
   Testet einzelne Methoden oder Klassen isoliert, z. B. `calculatePrice()`. Wird meist automatisiert mit JUnit/NUnit durchgeführt.

2. **Integrationstest:**  
   Testet das Zusammenspiel mehrerer Module, z. B. Preisberechnung + Datenhaltung. Ziel: Schnittstellen funktionieren.

3. **Systemtest:**  
   Testet das gesamte System aus Sicht des Anwenders. Oft manuell oder mit UI-Testtools.

**Durchführung:**  
Tests werden manuell oder automatisiert ausgeführt und die tatsächlichen Ergebnisse mit den erwarteten verglichen.

---

## Aufgabe 2: Beispiele für SW-Fehler, SW-Mangel und hoher Schaden

- **Beispiel Software-Fehler (Bug):**  
  Eine Buchhaltungssoftware berechnet die Mehrwertsteuer falsch wegen eines fehlerhaften `if`-Zweigs.

- **Beispiel Software-Mangel:**  
  In der Anforderung steht „Rabatt nur auf den Grundpreis anwenden“, die gelieferte Software zieht den Rabatt aber auch von den Zubehörpreisen ab. Die Software läuft, aber entspricht nicht der Spezifikation.

- **Beispiel hoher Schaden:**  
  Ein Abrechnungssystem einer Bank berechnet Zinsen oder Wechselkurse falsch und verursacht dadurch hohe finanzielle Verluste.

---

## Aufgabe 3: Testtreiber für `calculatePrice()`

Die Aufgabe sagt:  
- Händlerrabatt gilt **nur auf den Grundpreis**.  
- Zubehör-Rabatt (10 % ab 3 Extras, 15 % ab 5 Extras) gilt **nur auf den Zubehörpreis**.  
- Sondermodellaufschlag wird einfach addiert.

### Java-Code

```java
public class PriceCalculationTest {

    public static void main(String[] args) {
        boolean ok = test_calculate_price();
        System.out.println(ok ? "All tests passed." : "Some tests failed.");
    }

    // führt mehrere Testfälle aus
    static boolean test_calculate_price() {
        boolean test_ok = true;

        // 1) 3 Extras → 10% nur auf Extras, 10% Händler auf Grundpreis
        double price = calculatePrice(1000, 200, 300, 3, 10);
        // base: 1000 -> 900
        // extras: 300 -> 270
        // total: 900 + 200 + 270 = 1370
        if (Math.abs(price - 1370.0) > 0.01) {
            System.out.println("Test 1 failed. Got: " + price);
            test_ok = false;
        }

        // 2) 5 Extras → 15% nur auf Extras, 15% Händler auf Grundpreis
        price = calculatePrice(1000, 200, 300, 5, 15);
        // base: 1000 -> 850
        // extras: 300 -> 255
        // total: 850 + 200 + 255 = 1305
        if (Math.abs(price - 1305.0) > 0.01) {
            System.out.println("Test 2 failed. Got: " + price);
            test_ok = false;
        }

        // 3) keine Extras-Rabatte, aber Händlerrabatt
        price = calculatePrice(1000, 200, 300, 1, 5);
        // base: 1000 -> 950
        // extras: 300 -> 300
        // total: 950 + 200 + 300 = 1450
        if (Math.abs(price - 1450.0) > 0.01) {
            System.out.println("Test 3 failed. Got: " + price);
            test_ok = false;
        }

        return test_ok;
    }

    /**
     * Preisberechnung gemäss Aufgabenstellung:
     * - Händlerrabatt nur auf Grundpreis
     * - Zubehör-Rabatt nur auf Extras
     */
    static double calculatePrice(double baseprice,
                                 double specialprice,
                                 double extraprice,
                                 int extras,
                                 double discount) {

        double addonDiscount;

        // ab 5 Extras 15%, sonst ab 3 Extras 10%
        if (extras >= 5) {
            addonDiscount = 15.0;
        } else if (extras >= 3) {
            addonDiscount = 10.0;
        } else {
            addonDiscount = 0.0;
        }

        // Rabatt nur auf Grundpreis
        double baseWithDiscount = baseprice * (100.0 - discount) / 100.0;

        // Zubehör-Rabatt nur auf Extras
        double extrasWithDiscount = extraprice * (100.0 - addonDiscount) / 100.0;

        // alles zusammen
        return baseWithDiscount + specialprice + extrasWithDiscount;
    }
}
    }
}
```

## Aufgabe 3 – Bonus: Fehleranalyse

**Fehler 1 – Falsche Reihenfolge der Bedingungen:**  
Im ursprünglichen Code wurde zuerst `extras >= 3` geprüft und danach `extras >= 5`.  
Dadurch wurde der 15%-Rabatt nie angewendet, weil die erste Bedingung schon bei 5 Extras wahr war.  

**Korrektur:**  
```java
if (extras >= 5)
    addon_discount = 15;
else if (extras >= 3)
    addon_discount = 10;
else
    addon_discount = 0;

