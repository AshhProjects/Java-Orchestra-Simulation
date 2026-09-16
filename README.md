# Java-Orchestra-Simulation

## Overview
This repository contains my foundational Java coursework, culminating in a complex Orchestra Simulation program. The main project simulates the management of musicians, conductors, and musical compositions, including converting string-based musical notes into MIDI IDs.

## Repository Structure
* `/lab01` - `/lab9`: Foundational programming exercises covering data structures, object-oriented principles, and I/O handling in Java.
* `/coursework`: The core orchestra simulation project.
* `/extension`: An expanded version of the simulation featuring a custom Save/Load system (`.save` files) to preserve the simulation state across sessions.

## Key Features (Extension Project)
* **Dynamic Musician Allocation:** Automatically calculates the optimal number of Pianists, Cellists, and Violinists needed for a given set of compositions.
* **MIDI Conversion:** Parses string representations (e.g., "Bb7") into MIDI IDs.
* **State Persistence:** Allows users to save the current year, active compositions, and seating arrangements to a `.save` file and reload them later.
* **Custom Exception Handling:** Implements robust I/O checking (e.g., `BadFileException`).

## How to Run
To run the simulation with the extension, compile the Java files and run the following command in your terminal:

```bash
java EcsBandAid musicians.txt compositions.txt 10
```
(Arguments represent: Musician list, Composition list, and Number of simulation years).
