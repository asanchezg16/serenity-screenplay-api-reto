# 🎭 Automatización Web - Sauce Demo

Framework de automatización web utilizando **Serenity BDD** con el patrón de diseño **Screenplay**, **Cucumber** y **JUnit**.

[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.0.12-green)](https://serenity-bdd.github.io/)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org/)
[![Gradle](https://img.shields.io/badge/Gradle-8.5-blue)](https://gradle.org/)

---

## 📚 Documentación de Referencia

- [Serenity BDD Documentation](https://serenity-bdd.github.io/theserenitybook/latest/index.html)
- [Screenplay Pattern Guide](https://serenity-js.org/handbook/design/screenplay-pattern/)
- [Cucumber Reference](https://cucumber.io/docs/cucumber/)

---

## ⚙️ Requisitos Previos

- **Java 21** (Amazon Corretto)
- **Gradle 8.5+** (incluido en el proyecto via wrapper)
- **Chrome Browser** (última versión)
- **Git** (opcional, para clonar el repositorio)

### Verificar Instalación

```bash
java -version     # Debe mostrar Java 21
./gradlew --version
```

---

## 🏗️ Estructura del Proyecto

```
proyecto/
├── src/
│   └── test/
│       ├── java/com/choucair/
│       │   └── web/
│       │       ├── models/           # POJOs (LoginModel)
│       │       ├── userinterface/    # Page Objects con Targets
│       │       │   ├── LoginPage.java
│       │       │   ├── ProductsPage.java
│       │       │   └── CartPage.java
│       │       ├── tasks/            # Tareas del usuario (Screenplay)
│       │       │   ├── Login.java
│       │       │   ├── NavigateTo.java
│       │       │   └── AddProductToCart.java
│       │       └── questions/        # Validaciones
│       │           ├── TheLoginResult.java
│       │           ├── TheCartBadge.java
│       │           └── TheProductInCart.java
│       │
│       ├── stepdefinitions/
│       │   └── web/
│       │       ├── LoginWebSteps.java
│       │       └── AddToCartStepDefinitions.java
│       │
│       ├── runners/
│       │   └── WebTestSuite.java    # Runner principal
│       │
│       └── resources/
│           ├── features/web/
│           │   ├── Login.feature
│           │   └── AddToCart.feature
│           ├── serenity.conf         # Configuración de Serenity
│           └── serenity.properties   # Propiedades del proyecto
│
└── build.gradle                      # Dependencias y configuración
```

---

## 🎯 Flujos Automatizados

### 1️⃣ **Login Exitoso** ✅
- **Feature**: `Login.feature`
- **Escenarios**:
    - Login con credenciales válidas
    - Login con credenciales inválidas (negativo)
- **URL**: https://www.saucedemo.com/
- **Credenciales de prueba**:
    - Username: `standard_user`
    - Password: `secret_sauce`

### 2️⃣ **Agregar Producto al Carrito** ✅
- **Feature**: `AddToCart.feature`
- **Escenarios**:
    - Agregar un producto al carrito exitosamente
    - Validar cantidad en el badge del carrito
    - Validar producto en el carrito
- **Productos de prueba**: Sauce Labs Backpack, Sauce Labs Bike Light

---

## 🚀 Ejecución de Pruebas

### ⚡ Ejecutar TODAS las pruebas web

```bash
./gradlew clean test aggregate
```

---

## 🎮 Ejecución por Tags

### Tags Disponibles

| Tag | Descripción |
|-----|-------------|
| `@web` | Todos los tests web |
| `@smoke` | Pruebas críticas (smoke tests) |
| `@login` | Tests de login |
| `@cart` | Tests del carrito de compras |
| `@negative` | Casos negativos |

### Ejemplos de Ejecución

```bash
# Solo tests con tag @smoke
./gradlew test -Dcucumber.filter.tags="@smoke" aggregate

# Solo tests de login
./gradlew test -Dcucumber.filter.tags="@login" aggregate

# Solo tests del carrito
./gradlew test -Dcucumber.filter.tags="@cart" aggregate

# Smoke tests excluyendo negativos
./gradlew test -Dcucumber.filter.tags="@smoke and not @negative" aggregate

# Combinando tags (AND)
./gradlew test -Dcucumber.filter.tags="@web and @smoke" aggregate
```

---

## 📊 Generación de Reportes

Los reportes de Serenity se generan automáticamente después de cada ejecución en:

```
target/site/serenity/index.html
```

### Abrir el reporte

```bash
# Mac/Linux
open target/site/serenity/index.html

# Windows
start target/site/serenity/index.html
```

### Características del reporte:
- ✅ Screenshots de cada paso
- ✅ Desglose detallado de cada escenario
- ✅ Estadísticas de ejecución
- ✅ Trazabilidad completa

---

## 🔧 Configuración

### serenity.conf

Configuración principal del framework:

```hocon
webdriver {
  driver = chrome
  autodownload = true
  timeouts {
    implicitlywait = 10000
    fluentwait = 10000
  }
  wait.for.timeout = 15000
}

environments {
  web {
    chrome {
      webdriver.driver = chrome
      webdriver.base.url = "https://www.saucedemo.com"
      webdriver.wait.for.timeout = 15000
      webdriver.capabilities.browserName = "chrome"

      webdriver.capabilities."goog:chromeOptions" {
        args = [
          "start-maximized",
          "disable-infobars",
          "remote-allow-origins=*",
          "incognito",
          "disable-gpu",
          "disable-default-apps",
          "disable-popup-blocking"
        ]
      }
    }
  }
}
```

### Variables de Environment

```bash
# Cambiar driver
-Dwebdriver.driver=chrome

# Cambiar URL base
-Dwebdriver.base.url="https://otra-url.com"

# Filtrar por tags
-Dcucumber.filter.tags="@smoke"

# Modo headless
-Dheadless.mode=true

# Nivel de log
-Dserenity.logging=VERBOSE
```

---

## 👥 Patrón Screenplay

Este proyecto implementa el patrón **Screenplay** de Serenity BDD:

### **Actores (Actors)**
```java
theActorInTheSpotlight()  // El usuario actual en ejecución
```

### **Tareas (Tasks)**
Acciones de negocio de alto nivel:
```java
Login.withCredentials(loginModel)
AddProductToCart.called("Sauce Labs Backpack")
```

### **Interacciones (Interactions)**
Acciones técnicas específicas de Serenity:
```java
Click.on(target)
Enter.theValue(text).into(target)
Open.url(urlString)
```

### **Preguntas (Questions)**
Validaciones del estado del sistema:
```java
TheCartBadge.value()  // Retorna: String
TheProductInCart.called(productName)  // Retorna: Boolean
```

### **User Interfaces**
Definición de elementos de la página:
```java
public static final Target CART_BADGE = Target.the("contador del carrito")
    .located(By.cssSelector("[data-test='shopping-cart-badge']"));
```

---

## 🎓 Conceptos Clave

### **1. Localizadores con data-test**

Priorizamos `data-test` para mayor estabilidad:

```java
// ✅ Recomendado
By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")

// ⚠️ Menos estable
By.className("btn_inventory")
```

**Ventaja**: Los atributos `data-test` son específicos para testing y no cambian con rediseños visuales.

### **2. Métodos parametrizados**

Para reutilización de código:

```java
public static Target ADD_TO_CART_BUTTON(String productName) {
    String productId = productName.toLowerCase().replace(" ", "-");
    return Target.the("botón agregar al carrito de " + productName)
            .located(By.cssSelector("[data-test='add-to-cart-" + productId + "']"));
}
```

### **3. Lombok para modelos**

Reduce código boilerplate:

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {
    private String username;
    private String password;
}
```

### **4. Timeouts globales**

Configurados en `serenity.conf` para evitar esperas explícitas en el código:

```hocon
webdriver {
  timeouts {
    implicitlywait = 10000
    fluentwait = 10000
  }
  wait.for.timeout = 15000
}
```

---

## 📦 Dependencias Principales

| Dependencia | Versión | Propósito |
|-------------|---------|-----------|
| Serenity BDD Core | 4.0.12 | Framework base |
| Serenity Screenplay | 4.0.12 | Patrón Screenplay |
| Serenity Cucumber | 4.0.12 | Integración BDD |
| Cucumber Java | 7.14.0 | Gherkin DSL |
| JUnit | 5.10.0 | Test runner |
| Lombok | 1.18.36 | Reducir boilerplate |

---

## 🛠️ Buenas Prácticas Implementadas

### ✅ **Organización del código**
- Separación clara entre Tasks, Questions y User Interfaces
- Un package por responsabilidad
- Nombres descriptivos en español para steps

### ✅ **Localizadores**
- Prioridad: `data-test` > `id` > `cssSelector` > `xpath`
- Localizadores dinámicos con parámetros
- Documentación con JavaDoc

### ✅ **Esperas**
- Timeouts globales configurados
- Sin esperas explícitas innecesarias
- Serenity maneja automáticamente las esperas

### ✅ **Mantenibilidad**
- Código DRY (Don't Repeat Yourself)
- Métodos reutilizables
- Configuración centralizada

### ✅ **Legibilidad**
- Patrón Screenplay para código legible
- Gherkin en español
- Nombres claros y descriptivos

---

## 🚨 Notas Importantes

### Credenciales de Prueba

| Usuario | Password | Comportamiento |
|---------|----------|----------------|
| `standard_user` | `secret_sauce` | ✅ Usuario normal |
| `locked_out_user` | `secret_sauce` | ❌ Usuario bloqueado |
| `problem_user` | `secret_sauce` | ⚠️ Usuario con problemas |

### Productos Disponibles

- Sauce Labs Backpack - $29.99
- Sauce Labs Bike Light - $9.99
- Sauce Labs Bolt T-Shirt - $15.99
- Sauce Labs Fleece Jacket - $49.99
- Sauce Labs Onesie - $7.99
- Test.allTheThings() T-Shirt (Red) - $15.99

### Chrome y ChromeDriver

- ✅ Serenity descarga automáticamente el ChromeDriver correcto
- ✅ No requiere instalación manual
- ✅ Compatible con la última versión de Chrome

---

## 📝 Ejemplos de Comandos Completos

### Ejecución básica

```bash
# Todo el proyecto
./gradlew clean test aggregate

# Solo smoke tests
./gradlew test -Dcucumber.filter.tags="@smoke" aggregate

# Solo login
./gradlew test -Dcucumber.filter.tags="@login" aggregate
```

### Ver logs detallados

```bash
# Modo verbose
./gradlew test --tests runners.WebTestSuite --info

# Modo debug
./gradlew test --tests runners.WebTestSuite --debug
```

### Limpiar y ejecutar

```bash
# Limpiar outputs anteriores
./gradlew clean

# Ejecutar y generar reportes
./gradlew test aggregate
```

---

## 🎯 Estructura de un Feature

```gherkin
# language: es
@web @smoke
Característica: [Nombre de la funcionalidad]
  Como [tipo de usuario]
  Quiero [objetivo]
  Para [beneficio]

  Antecedentes:
    Dado [precondición común]

  @tag_especifico
  Escenario: [Descripción del escenario]
    Cuando [acción]
    Entonces [resultado esperado]
```

---

## 📧 Soporte

Para preguntas o soporte relacionado con el framework, contacta al equipo de QA.

---

## 📄 Licencia

Este proyecto es de uso educativo y demostrativo para el reto de automatización.

---

**Happy Testing! 🚀🧪**