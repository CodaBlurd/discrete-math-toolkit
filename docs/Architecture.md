# Architecture

## Architectural Style

Layered Architecture

```text
Presentation Layer
        ↓
Application Layer
        ↓
Domain Layer
```

---

# Design Principles

## Single Responsibility Principle

Each class should have one reason to change.

## Open/Closed Principle

Algorithms should be extendable without modifying existing code.

## Separation of Concerns

User interaction, business logic, and infrastructure should remain separated.

---

# Package Structure

```text
com.group.keencodelabs

├── app
├── common
├── numbers
├── sets
├── logic
├── combinatorics
├── relations
├── graphs
```

---

# Module Responsibilities

## app

Application entry points.

Examples:

```text
Main
MenuHandler
CommandProcessor
```

---

## numbers

Number systems and number theory.

Examples:

```text
NumberConverter
BaseConverter
PrimeChecker
GcdCalculator
LcmCalculator
ModularArithmetic
ConversionResult
```

---

## sets

Set operations.

Examples:

```text
SetOperations
PowerSetGenerator
CartesianProductGenerator
```

---

## logic

Logic and truth tables.

Examples:

```text
TruthTableGenerator
LogicalExpression
LogicalOperator
```

---

## combinatorics

Combinatorial mathematics.

Examples:

```text
FactorialCalculator
PermutationCalculator
CombinationCalculator
```

---

## relations

Relation analysis.

Examples:

```text
Relation
RelationAnalyzer
RelationProperty
```

---

## graphs

Graph structures and traversal algorithms.

Examples:

```text
Graph
Vertex
Edge
BfsTraversal
DfsTraversal
```

---

# Testing Strategy

```text
src
├── main
└── test
```

Every algorithm implementation should have corresponding tests.

Examples:

```text
BaseConverterTest
PrimeCheckerTest
GcdCalculatorTest
```

---

# Future Design Considerations

Potential Strategy Pattern Candidates:

```text
NumberConverter
    |
    +-- BaseConverter
```

```text
GraphTraversal
    |
    +-- BfsTraversal
    +-- DfsTraversal
```

Avoid introducing patterns until they solve a real problem.
