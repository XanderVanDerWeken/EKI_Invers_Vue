# EKI_Invers
## Über das Projekt
Projekt für Modul "Einführung Künstliche Intelligenz". Durchgeführt durch Xander Van der Weken.

## Verwendete Technologien
| Art               | Software           |
|-------------------|--------------------|
| Diagramme         | Draw.io            |
| IDE               | IntelliJ Ultimate  |
| Backend Techstack | Maven, Ktor         |
| Frontend Techstack| npm, VueJs3, Vite.js, nginx|
| Version Control   | Github             |

## Module
Dieses Projekt besteht aus 2 Modulen. Aus dem Backend Module, welches in Kotlin geschrieben wurde und die Spiellogik über Rest API zur Verfügung stellt. \
Und das Frontend mit Typescript und VueJs 3 geschrieben, welches Nutzerinteraktion ermöglicht.

## Installation
1. Klonen des Projekts
```bash
  git clone https://github.com/XanderVanDerWeken/EKI_Invers_Vue.git
```
2. Für das Ausführen im Produktionsmodus wird ebenfalls Docker benötigt.

## Ausführen in Entwicklungs-Modus
Befehle aus der Sicht des Hauptverzeichnisses. \
Für beide Schritte werden verschiedene Terminal Fenster benutzt
1. Starten des Servers
```bash
# In Backend Ordner wechseln
cd invers_backend
# Projekt in Jar packen
mvn clean install
# Jar ausführen
java -jar target/invers_server.jar
```

2. Starten des Frontends
```bash
# In Frontend Ordner wechseln
cd invers_frontend
# dev Ausführung über Vite.js starten
npm run dev
```
3. Im Browser folgende [localhost:5173](http://localhost:5173) aufrufen.

## Ausführen in Produktions-Modus
Befehle aus der Sicht des Hauptverzeichnisses.
1. Starten des Docker-Compose Netzwerkes
```bash
docker compose up
```

2. Wenn das Netzwerk läuft, im Browser folgende [localhost:8081](http://localhost:8081) aufrufen.

## Bekannte Bugs
- ...

## Dokumentation
Gesamte Dokumentation is in der [GitHub Wiki](https://github.com/XanderVanDerWeken/EKI_Invers_Vue/wiki) zufinden
