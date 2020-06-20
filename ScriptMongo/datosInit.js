conn = new Mongo();

db = conn.getDB("pizzeria");
db.dropDatabase()
db.createCollection('usuarios')
db.usuarios.insert({"nombre": "Georges", "apellidos":"Alfaro Salazar", "cedula": "105710421", "direccion": "San Jose", "contraseña": "admin", "telefono": "89104578","rol": true})
db.usuarios.insert({"nombre": "Carlos", "apellidos":"Zhou Zheng", "cedula": "504250413" ,"direccion": "Guanacaste", "contraseña": "chinito123", "telefono": "60712317","rol": false})

db.createCollection('pizzas')
db.pizzas.insert({"nombre": "Suprema", "ingredientes":["pepperoni", "carne de res", "carne de cerdo","chile dulce","hongos","cebolla"],"precioBase": 2000.0})
db.pizzas.insert({"nombre": "Jamon y queso", "ingredientes":["jamon", "queso"],"precioBase": 1500.0})
db.pizzas.insert({"nombre": "Pepperoni", "ingredientes":["pepperoni","queso"],"precioBase": 1500.0})
db.pizzas.insert({"nombre": "Hawaiana", "ingredientes":["jamon","piña", "coco"],"precioBase": 2000.0})
db.pizzas.insert({"nombre": "Napolitana", "ingredientes":["tomate en rodajas", "oregano"],"precioBase": 2000.0})


db.createCollection('ingredientes')
db.ingredientes.insert({"nombre": "pepperoni", "precio": 200})
db.ingredientes.insert({"nombre": "carne de res", "precio": 200})
db.ingredientes.insert({"nombre": "carne de cerdo", "precio": 200})
db.ingredientes.insert({"nombre": "hongos", "precio": 200})
db.ingredientes.insert({"nombre": "jamon", "precio": 200})
db.ingredientes.insert({"nombre": "piña", "precio": 200})
db.ingredientes.insert({"nombre": "queso", "precio": 200})
db.ingredientes.insert({"nombre": "coco", "precio": 200})
db.ingredientes.insert({"nombre": "chile dulce", "precio": 200})
db.ingredientes.insert({"nombre": "cebolla", "precio": 200})
db.ingredientes.insert({"nombre": "tomate en rodajas", "precio": 200})
db.ingredientes.insert({"nombre": "pollo", "precio": 200})
db.ingredientes.insert({"nombre": "tocineta", "precio": 200})
db.ingredientes.insert({"nombre": "pesto", "precio": 200})
db.ingredientes.insert({"nombre": "aceitunas", "precio": 200})
db.ingredientes.insert({"nombre": "albahaca", "precio": 200})
db.ingredientes.insert({"nombre": "oregano", "precio": 200})


db.createCollection('bebidas')
db.bebidas.insert({"nombre": "Coca-cola", "precio": 2000})
db.bebidas.insert({"nombre": "Fuze Tea", "precio": 2000})
db.bebidas.insert({"nombre": "Fanta Naranja", "precio": 2000})
db.bebidas.insert({"nombre": "Fanta Kolita", "precio": 2000})

db.createCollection('complementos')
db.complementos.insert({"nombre": "Papas Fritas", "precio": 2000})
db.complementos.insert({"nombre": "Cheese Poppers", "precio": 1600})
db.complementos.insert({"nombre": "Pan de ajo", "precio": 1500})
db.complementos.insert({"nombre": "BreadSticks", "precio": 1530})


printjson(db.complementos.find().toArray());

db.createCollection('metodosPago')
db.metodosPago.insert({"nombre": "Contado"})
db.metodosPago.insert({"nombre": "Tarjeta"})





