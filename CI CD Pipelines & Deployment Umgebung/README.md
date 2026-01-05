# Deployment-Umgebungen

## Aufgabe 1: Zuordnung der Tools zu Umgebungen

### Docker Compose
- **Geeignet für:** Development, Testing
- **Begründung:** Schnell, einfach, lokal ausführbar, ideal für reproduzierbare Setups

### Kubernetes
- **Geeignet für:** Staging, Production
- **Begründung:** Skalierbar, Self-Healing, produktionsnahes Deployment

### Vagrant
- **Geeignet für:** Development
- **Begründung:** Reproduzierbare VMs, sinnvoll bei OS-nahen Tests

### Terraform
- **Geeignet für:** Staging, Production
- **Begründung:** Infrastructure as Code für konsistente Umgebungen

### Zusammenfassung
- **Development:** Docker Compose / Vagrant  
- **Testing:** Docker Compose  
- **Staging:** Kubernetes + Terraform  
- **Production:** Kubernetes + Terraform  

---

## Aufgabe 2: Gewählte Lösung & Reflexion

### Gewählte Software
- **Tool:** Docker Compose
- **Umgebung:** Testing

### Ziel
- Automatisches Setup einer Testumgebung mit mehreren Services

### Beispiel-Setup
- PostgreSQL (Datenbank)
- Adminer
- Optional: Backend (z.B. Spring Boot)

### Beobachtete Probleme
- Port-Konflikte
- Service startet bevor DB bereit ist
- Persistente Volumes bei Tests

### Fazit
Docker Compose eignet sich sehr gut für Development- und Test-Umgebungen.  
Für produktive und skalierbare Systeme sind Kubernetes und Terraform sinnvoller.

### Reflexion

Docker Compose ermöglicht ein schnelles und einfaches Aufsetzen von Entwicklungs- und Testumgebungen.  
Das Setup ist übersichtlich, reproduzierbar und ohne grossen Konfigurationsaufwand umsetzbar.  
Grenzen zeigen sich bei Skalierung und produktivem Einsatz, weshalb für Staging und Production andere Tools wie Kuber


---

# Recipe Planner
## Aufgabe 1:
### Getestete Inhalte

**Controller Tests**
- Alle Endpoints des `RecipeController` wurden mit MockMvc getestet
- Rückgabestatus (HTTP 200) und JSON-Struktur wurden überprüft
- Service-Abhängigkeiten wurden gemockt

**Mapper Tests**
- `IngredientEntityMapper`: Mapping von Entity zu Domain und zurück
- Mapping von Listen (Entity ↔ Domain)
- `RecipeEntityMapper`: Mapping aller Felder inkl. verschachtelter Ingredients
- SoftAssertions wurden verwendet, um mehrere Felder in einem Testlauf zu prüfen

## Aufgabe 2 – Test Reports

Die Unit Tests werden automatisiert mit Maven Surefire ausgeführt.
Die Test-Reports werden unter `target/surefire-reports` erstellt.

Zusätzlich ist JaCoCo eingebunden. Bei `mvn verify` wird ein HTML-Coverage-Report
unter `target/site/jacoco/index.html` generiert und im Browser einsehbar.
<img width="1919" height="501" alt="image" src="https://github.com/user-attachments/assets/cad07719-2f0b-4333-a597-81b8a0083603" />

## Aufgabe 3 – Pipeline

Es wurde eine Build-Pipeline mit GitHub Actions eingerichtet.
Bei jedem Push oder Pull Request wird die Pipeline automatisch gestartet.

Die Pipeline führt das Backend mit Java 17 aus und startet die Unit Tests
mittels Maven (`mvn verify`).

Pro Pipeline-Durchlauf werden die Test-Reports (Maven Surefire) sowie
ein JaCoCo HTML-Coverage-Report generiert und als Artefakte in GitHub
bereitgestellt, wo sie eingesehen werden können.

