# Store Management Tool

Spring Boot-based Java application demonstrating RESTful API development with custom repositories and Hibernate Search.

# Features

## Routes

| Routes/Controllers                     | Description                         | Parameters                                              |
|----------------------------------------|-------------------------------------|---------------------------------------------------------|
| **BrowseController**                   | Controller for all read-only routes |
| `/browse/markets`                      | All markets in which stores exist   | None                                                    |
| `/browse/markets/{market}`             | Details of a particular market.     | `{market}` Market name                                  |
| `/browse/markets/{market}/stores`      | All stores in a market              | `{market}` Market name                                  |
| `/browse/stores`                       | All stores                          | None                                                    |
| `/browse/stores/{store}`               | Details of a particular store       | `{store}` Store name                                    |
| `/browse/stores/{store}/sections`      | Sections of a store                 | `{store}` Store name                                    |
| `/browse/sections/{sectionId}`         | Details of a section                | `{sectionId}` Section ID                                |
| `/browse/sections/{sectionId}/entries` | Entries in a section                | `{sectionId}` Section ID                                |
| `/browse/products/{productId}`         | Details of a product                | `{productId}` Product ID                                |
| **FindProductController**              | Hibernate Search-based search route |
| `/find/products?search&limit`          | Fuzzy product search                | `search` Search term, `limit` Maximum number of results |
| **ManageStoreService**                 | Writing routes                      |
| `/manage/set-price`                    | Update the price of a product       | JSON Body: `{productId, storeSectionId, price}`         |

## Security

* The application offers a username/password authentication method. The fixture contains a single user with the name
  `user`
  and password `password`.
* Sessions are enabled, allowing only one login per session. An attempt was made to implement a two-step authentication
  process (password followed by JWT token) similar to OAuth2, but it was not completed due to time constraints.

# Structure

## Data sources

| Data Source Name | Description                    |
|------------------|--------------------------------|
| `dataSource`     | Data for the store information |
| `authDataSource` | User login information         | 

* Separating data sources enhances security and development efficiency by restricting access to sensitive information.

* Each data source has an associated entity manager and corresponding entities.

## Repositories

* Repositories exist for entities which are queried from the services.

Two custom repositories were added:

* `ProductCustomRepositoryImpl` for the fuzzy search using `HibernateSearch`
* `StoreSectionCustomRepositoryImpl` for the query: `getStoreSectionsByProduct`

### Services

Each controller has its own service that handles all necessary data access through repositories.
Each service encapsulates its data responses in **DTO**s.

Although I find DTOs somewhat bureaucratic, they are useful for applications that need to represent the same data in
different formats across various routes or need to hide certain fields for security or clarity.