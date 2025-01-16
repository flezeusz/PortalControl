# PortalControl
**PortalControl** - plugin do Minecraft, który umożliwia zarządzanie dostępem do wymiarów, z pełną konfiguracją dostosowaną do potrzeb serwera.

### Wersja: Spigot 1.17.1+

## 🌟 Funkcje
- **Zarządzanie wymiarami** - Włączaj i wyłączaj Nether lub End
- **Ogłoszenia na ekranie** - informuj graczy o zmianach w dostępności wymiarów
- **Komunikaty na chacie** - informuj graczy, którzy próbują przejść do nieaktywnych wymiarów o ich zamknięciu  
- **Automatyczna teleportacja** - przenosi graczy na ich spawn po zamknięciu wymiaru
- **Pełna konfigurowalność** - dostosuj wszystkie funkcje pluginu do swoich potrzeb

## 🛠️ Komendy
`/portal nether <on/off>` - zarządzanie dostępnością Netheru

`/portal end <on/off>` - zarządzanie dostępnością Endu

`/portal reload` - przeładowanie pliku konfiguracyjnego

## 🧰 Konfiguracja
W configu możesz:
- włączyć lub wyłączyć ogłoszenia o otwarciu/zamknięciu wymiaru
- ustawić własne ogłoszenie z możliwością dodania dźwięku
- wybrać czy po zamknięciu wymiaru ma teleportować graczy na ich spawna
- włączyć i dostosować komunikat na chacie dla graczy którzy próbują przejść do nieaktywnego wymiaru

## 🔒 Permisje
`flezy.portal.command` – dostęp do komend zarządzania wymiarami (domyślnie dla operatorów)

`flezy.portal.bypass` – pozwala graczowi przechodzić między wymiarami, nawet jeśli są zablokowane (domyślnie dla operatorów)
