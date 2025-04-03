# MarssApp 🚀

Απλή CRUD εφαρμογή διαχείρισης πόρων (Resources), βασισμένη σε Spring Boot και H2 βάση δεδομένων.  
Υλοποιήθηκε ως εργασία για το μάθημα Τεχνολογίες Λογισμικού.

## 📦 Τεχνολογίες

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Docker
- GitHub Actions (CI)

## 🧱 Αρχιτεκτονική

Η εφαρμογή ακολουθεί τη δομή MVC:

```
src
├── main
│   ├── java/com/marsops
│   │   ├── controller        # REST API
│   │   ├── model             # Οντότητα Resource
│   │   └── repository        # Spring Data JPA Repository
│   └── resources             # application.properties κλπ
├── test                     # Test classes (JUnit + Mockito)
```

## 🔍 Τι υποστηρίζει

- Προβολή όλων των Resources (`GET /api/resources`)
- Προβολή Resource ανά ID (`GET /api/resources/{id}`)
- Προσθήκη Resource (`POST /api/resources`)
- Ενημέρωση Resource (`PUT /api/resources/{id}`)
- Διαγραφή Resource (`DELETE /api/resources/{id}`)

## ▶️ Τρέξιμο εφαρμογής

```bash
mvn spring-boot:run
```

`H2 Console` διαθέσιμη στο `http://localhost:8080/h2-console`

---

## 🐳 Τρέξιμο με Docker

```bash
docker build -t marssapp .
docker run -p 8080:8080 marssapp
```

---

## 🧪 Τεστς

Τεστ ελέγχου REST controller με χρήση `MockMvc` και `Mockito`:

```bash
mvn test
```

---

## ⚙️ Continuous Integration (CI)

Η εφαρμογή συνοδεύεται από GitHub Actions Workflow:

- Αυτόματο build με `mvn clean package`
- Εκτέλεση unit tests
- Trigger σε κάθε `push` ή `pull request` στο `main` branch

```yaml
.github/workflows/maven.yml
```

---

## 👨‍💻 Συντελεστής

**Nikos Laspias**  
[github.com/NikosLaspias](https://github.com/NikosLaspias)
