# Online-store-RestAPI
Restful service simulating online store

The service simulates a store in a Mall shopping center.
The Store has **Catalogs** which contain **Items**.

The API has 3 table 
- users - containing the users in the mall - both owners and customers (id,name,role,payment_method)
- item - contain the items in the store (id,name,price,amount,catalog_id)
- catalog - contains info regarding hte catalog (id, name)

Access to catalogs and items is allowed for user of the shop - located in Users table.
One is allowed to get all items and catalogs without being in the DB, but not browse it or perform actions on it.

Exposed endpoints for Catalog:

:triangular_flag_on_post:	**Get /catalog** - get all catalogs in the store.\
:triangular_flag_on_post:	**Get /catalog/{id}/{user}** - get Catalog by Id, user have to present in the DB.\
:triangular_flag_on_post:	**Post /catalog/{user}** - add new catalog, user must be owner.\
:triangular_flag_on_post:	**Patch /catalog/{user}** - edit existing catalog, must be owner.\
:triangular_flag_on_post:	**Delete /catalog/{id}/{user}** - delete catalog by id, must be user.\

Exposed endpoints for Item:

:triangular_flag_on_post:	**Get /catalog/item** - get all items in the store.\
:triangular_flag_on_post:	**Get /catalog/item/{id}/{user}** - get item by Id, user have to present in the DB.\
:triangular_flag_on_post:	**Post /catalog/item/{user}** - add new item, user must be owner.\
:triangular_flag_on_post:	**Patch /catalog/item/{user}** - edit existing item, must be owner.\
:triangular_flag_on_post:	**Delete /catalog/item/{id}/{user}** - delete item by id, must be owner.\
:triangular_flag_on_post:	**Patch /catalog/item/purchase/{user}** - purchase item, must be customer. The returned Item is the purchased one, the result of subtruction is kept in the DB.
