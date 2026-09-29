# Project Audit

**Repository:** `hakmedevs-svg/FoodDeliveryApp`
**Audit date:** 2026-09-29
**Audit scope:** Phase 1 only — repository analysis and implementation planning. No feature implementation was started.

## Executive Summary

The repository is an early Android/XML prototype with a small Express backend. It is not yet an integrated production system. The Android client currently uses hardcoded restaurant data and local preference flags, while the backend reads JSON files and returns in-memory/mock-style responses. PostgreSQL, Prisma, real OTP authentication, JWT sessions, authoritative checkout, order persistence, realtime tracking, admin tooling, and automated integration tests are absent.

The existing Android/XML structure and useful location/settings work should be preserved where practical. The hardcoded/demo data and simulated login must be replaced incrementally, not extended as production behavior.

## Current Repository Structure

```text
/
├── app/                         Android application module
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── cpp/                 Referenced by Gradle; contents not yet inspected
│       ├── java/com/example/fooddelivery/
│       │   ├── Activities and restaurant prototype classes
│       │   ├── data/
│       │   └── ui/{login,main,restaurant,settings}/
│       └── res/{layout,values,values-ar,xml}
├── backend/
│   ├── server.js                Express server
│   ├── package.json
│   ├── .env.example
│   └── data/                    JSON governorates/restaurants data
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── README.md
└── .gitignore
```

## What Exists

### Android

- Kotlin Android application using XML layouts, ViewBinding/DataBinding, AppCompat, Material, RecyclerView, ViewModel/Lifecycle, Coroutines, Google location/maps, Firebase dependencies, and encrypted preferences.
- Manifest with internet and location permissions, an application class, login, governorate selection, main, settings, and restaurant activities.
- Existing feature areas for login, governorate selection, main restaurant browsing, restaurant details, language/theme/settings, privacy, terms, and about.
- Data classes and a preference manager.
- Location permission flow and distance sorting are present in the prototype.

### Backend

- Small Express server using CommonJS JavaScript.
- CORS and JSON middleware.
- Health endpoint at `/api/health`.
- JSON-backed governorate and restaurant endpoints.
- An order endpoint that validates only required request fields and returns an order object without persistence or server-side pricing.
- Optional Firebase Admin initialization based on environment variables.

### Repository Configuration

- Android Gradle Plugin 8.5.2, Kotlin 1.9.24, compile/target SDK 34, Java 17.
- `backend/.env.example` currently contains only Firebase and port placeholders.
- Root `.gitignore` excludes common Android build artifacts and local properties, but does not yet cover the complete backend secret/media/database requirements.

## What Works or Is Partially Implemented

- The project has a recognizable Android application shell and package structure.
- Existing screens can be used as a migration starting point for XML-based UI.
- Phone format validation exists locally.
- Location permission and last-known-location lookup exist locally.
- Restaurant detail navigation exists locally.
- Basic backend process and read endpoints are defined.

These are prototype capabilities only; they are not yet evidence of a production-ready connected flow.

## Critical Gaps and Risks

### Authentication and Security

- Login currently marks a user as logged in through local preferences after phone-format validation; there is no OTP challenge, server verification, access token, refresh token, or authorization.
- The backend has no PostgreSQL-backed users/sessions and no role enforcement.
- `usesCleartextTraffic="true"` is unsafe as a production default and must be restricted to an explicit local-development configuration.
- CORS, rate limits, secure headers, request validation, audit logging, and secret management are not implemented.

### Data and Backend

- No PostgreSQL, Prisma schema, migrations, transactions, indexes, or relational constraints.
- Governorates and restaurants are read from JSON files rather than a database.
- `/api/orders` does not persist orders, calculate authoritative totals, validate availability/address/zones/coupons, or implement a state machine.
- The backend is JavaScript rather than the planned TypeScript/NestJS architecture.
- No API versioning under `/api/v1`, consistent response envelope, pagination, structured errors, or automated API tests.

### Android Architecture

- The existing prototype contains business/data behavior in Activities and uses hardcoded restaurant records, including non-Iraqi sample locations/currency.
- No Retrofit/OkHttp API client, token manager, refresh handling, repository/use-case layer, Hilt graph, Room cache, WorkManager retry strategy, or WebSocket client is present in the inspected files.
- Firebase dependencies are present, but no production Firebase configuration was found in the inspected tree.
- The project uses XML/ViewBinding rather than Compose. Preserve this established UI technology unless a later migration is explicitly justified; do not mix frameworks unnecessarily.

### Infrastructure and Operations

- No Docker Compose, Redis/realtime infrastructure, admin web application, CI workflow, deployment configuration, observability, or environment separation exists.
- No documented database migration/seed process or production deployment process exists.
- The Gradle file references an external native build/CMake path; the native directory must be verified before deciding whether to remove it. No C++ should be added.

### Testing

- No backend unit/integration/API test structure was found.
- Only basic Android test dependencies are configured; feature-level tests and end-to-end coverage are absent.
- No build or test result was executed by this audit environment, so compilation status remains to be verified in Phase 2.

## Decisions for the Implementation

1. **Preserve XML Android UI initially.** It is already established in the repository; migrate toward layered architecture without an unnecessary Compose rewrite.
2. **Use TypeScript for the new backend foundation.** NestJS is suitable for the requested domain size, but existing Express endpoints should be replaced incrementally behind `/api/v1`, not duplicated indefinitely.
3. **Use PostgreSQL with Prisma.** JSON files must be limited to verified seed/import input where appropriate, never runtime production storage.
4. **Do not use C++ unless inspection proves a genuine native requirement.**
5. **Treat all current restaurant records and local login behavior as prototype code.** They must not be used as production data or authentication.
6. **Implement one vertical slice first:** health/configuration → database connection → phone OTP abstraction → authenticated profile/location → restaurant read API → Android API client. Then expand to cart and orders.

## Recommended Implementation Sequence

### Phase 2 — Architecture and Build Baseline

- Inspect all remaining Android resources, native files, backend JSON data, and package scripts.
- Run Android/backend build checks and record failures.
- Establish architecture documentation and remove only confirmed obsolete configuration.
- Add environment-aware configuration without committing secrets.

### Phase 3 — Backend Foundation

- Create TypeScript backend structure, dependency injection, configuration, validation, logging, error handling, `/api/v1`, health checks, and test harness.
- Add PostgreSQL/Prisma connectivity and local Docker Compose.

### Phase 4–5 — Database and Iraqi Geography

- Add normalized schema, migrations, indexes, constraints, audit foundations, and verified geography import structure.
- Keep geographic hierarchy database-driven: governorate → city → district → neighborhood → delivery zone.

### Phase 6 — Real Authentication

- Add phone normalization, OTP provider abstraction, expiration/attempt/rate limits, JWT access tokens, refresh-token rotation, and role authorization.
- Keep development OTP behavior isolated and disabled by default in production.

### Phase 7–10 — Android Foundation and Connected Profile/Location

- Add Retrofit/OkHttp, secure token storage, API error mapping, repositories, ViewModels, Room-safe caching, and backend-backed profile/address flows.
- Replace local login success with the real API flow.

### Phase 11–21 — Commerce and Delivery

- Implement restaurants/branches, categories/products, cart, server-authoritative checkout, order state machine, drivers, delivery zones, realtime tracking, notifications, coupons, reviews, and payment abstraction.

### Phase 22–30 — Operations and Quality

- Add restaurant management, admin web application, security hardening, localization, performance work, automated tests, end-to-end tests, builds, deployment documentation, and final security review.

## Phase 1 Exit Criteria

- Existing implementation and technology choices documented.
- Prototype limitations explicitly identified.
- No major feature implementation started.
- Next phase defined as build/architecture baseline rather than mass file generation.

## Immediate Next Action

Proceed to **Phase 2** only after reviewing this audit: verify the complete Android resource/source tree and run the Android and backend baseline checks. Then implement the smallest build-safe foundation change required by the actual failures.
