1. Información general:
La tecnología está comenzando a dominar, y apoderarse de muchos 
campos sustituyendo sistemas análogos. Uno de esos sistemas es el de 
los restaurantes. Se piensa digitalizar el sistema de pedidos, control 
de horarios y puestos. Se proveerá a los restaurantes con tabletas con 
sistema operativo Windows.

3. Procedimiento:
En esta aplicación se utilizara java como back-end y javafx como front-end. 
Dada las características del lenguaje JavaFX la parte visual tendrá mucha 
énfasis. Debe contar con una base de datos para sql server y la aplicación debe 
estar correctamente configurada para que pueda obtener la configuracion de 
conexion de un archivo con extension .conf.

3. Funcionamiento
Básicamente contará con tres módulos:
	Módulo CRUD
		Contará con las operaciones CRUD correspondientes a todas las entidades de la 
		base de datos.
		
	Módulo Mesero
		Podra tomar las ordenes de los clientes, cada orden se manejara con cuatro 
		estados diferentes: espera, entregada, cancelada y pagada. Este módulo 
		podrá crear órdenes cuyo estado inicial será espera, cada cliente podra hacer 
		varios pedidos en una sola orden, y cada pedido puede constar de un platillo 
		que puede o no tener ingredientes extras o quitar ingredientes, o de algun 
		postre o bebida, cuando esa orden se entregue cambiará a estado entregado, 
		pero solo podrá poner ese estado el Módulo Chef. En caso de que el cliente 
		decida cancelar la orden, este módulo podrá cambiar su estado a cancelado. 
		El último estado es muy importante, ya que el mesero procesará el cargo en 
		efectivo o tarjeta de crédito, y podrá agrupar los pedidos de la orden. Luego de 
		esto se cambiará el estado a pagado.
		
	Módulo Chef
		Este podrá visualizar las órdenes con estado espera, y cambiar su estado a 
		entregado. Podrá visualizar los pedidos de la orden y los ingredientes extra o 
		a eliminar del pedido.
