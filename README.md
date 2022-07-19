<h1 align="center">Proyecto Final diplomado desarrollo movil</h1>
<h2 align="center">CULTIVAS</h2>
<h3 align="center"> Aplicación movil para el control de cultivos</h3>
 Presentado por por: 
 
    Diego Sachica
    
    Johnny Argote
    
    Marco Vanegas
    
    Andrés Siachoque
    
    
# Tabla de Contenidos
[1. Introducción](#introducción)

[2. Herramientas usadas para la solución](#herramientas-usadas-para-la-solución)

[3. Necesidad](#necesidad)

[4. Diseño de interfaz](#diseño-de-interfaz)

[5. Desarrollo Proyecto](#Desarrollo-Proyecto)

[6. Conclusiones](#Conclusiones)



# Introducción

La agricultura tiene una gran importancia en la economía de muchos países en desarrollo debido a su significativa contribución a la producción interna y el empleo, así como por su aporte a la seguridad alimentaria, esencial sobre todo para los países menos industrializados. Sin embargo, en todo el mundo en desarrollo la agricultura sigue afrontando dificultades. En los años 90 la producción agrícola per cápita tanto para el mercado interno como para el de exportación disminuyó, y a fines de la década de 1990 sólo aportaban el 1 por ciento de las exportaciones agrícolas mundiales. En efecto, a pesar de todas las oportunidades económicas atribuidas a la globalización y el comercio internacional, por lo general los pequeños campesinos del mundo en desarrollo no sólo no pueden participar en los mercados internacionales sino que tienen que competir con los productos de importación en sus propios mercados internos.

Antes de la pandemia, la agricultura como sector productivo no generaba el interés que concita en la actualidad como proveedor estratégico de alimentos, pilar de la seguridad alimentaria y bastión en la lucha contra la inflación. Estudios recientes comprueban que la contribución del valor agregado de la agricultura a la economía es muy superior en América Latina, incluida Colombia. Un estudio de la FAO (Organización de las Naciones Unidas para la Agricultura y la Alimentación) sobre Transformación Rural de Carolina Trivelli y Julio Berdegué, menciona que el aporte agropecuario ampliado a la economía podría ser del 30% en el país, al igual que, en promedio, en 9 países evaluados de la región. Los autores sostienen que el peso total de la agricultura ampliada representan un multiplicador de la economía agrícola, que podría elevar, en promedio, hasta por cuatro el impacto del valor real de la agricultura

Dicho esto, se decidió  que ayudar al agriculto colombiano a hacer parte de un mundo cada vez más globalizado e industrializado por medio de la implementación de una aplicación móvil que le permita tener un mayor control sobre sus cultivos garantizando una mejor calidad y eficiencia en cada uno de estos.

# Herramientas usadas para la solución
<h2>Kotlin</h2>

Kotlin es un lenguaje de programación estático de código abierto que admite la programación funcional y orientada a objetos lo que lo hace muy práctico de usar, además, en cuanto al tema de desarrollo de aplicaciones móviles android  es uno de los  más usados y con mayor soporte gracias a Google.

![Kotlin.jpg](https://i.postimg.cc/wTsZy7js/Kotlin.jpg)

<h2> Android studio </h2> 

Es el entorno de desarrollo integrado oficial para la plataforma Android, está basado en el software IntelliJ IDEA de JetBrain y soporta varios lenguajes de programacíón incluyendo Kotlin, esta basado en Gradle que es un emulador rápido y cargado de funciones.

[![Andorid-studio.jpg](https://i.postimg.cc/rsdh6YPP/Andorid-studio.png)](https://postimg.cc/q6TcyQ0s)

<h2> Figma </h2> 
La herramienta balsamiq nos permitirá crear el prototipo y modelado del proyecto, debido a que esta herramienta es una de las mejores 
para crear prototipos, bocetos o wireframes es Balsamiq Mockups


[![figma.jpg](https://i.postimg.cc/x17p5Qpb/figma.webp)](https://postimg.cc/NK6xG3cg)

Figma es una herramienta para diseñar prototipos, wireframes, interfaces… Todo aquello que posea una interfaz gráfica se puede diseñar desde Figma ya sean páginas web, pantallas de móvil e incluso para smartwatches, su mayor ventaja es que es una plataforma  colaborativa.

<h2> Firebase  </h2> 

Firebase básicamente es una plataforma móvil diseñada y creada por Google, teniendo como principal función desarrollar y facilitar la creación de aplicaciones para dispositivos móviles que cuenten con una alta calidad a pesar de su rápida elaboración.

[![firebase.png](https://i.postimg.cc/ZRZRMCw9/firebase.png)](https://postimg.cc/WDWTDbLj)

# Necesidad
Basados en el objetivo de desarrollo sostenible 9 titulado "Construir infraestructuras resilientes, promover la industrialización sostenible y fomentar la innovación" se obtiene que en los países en desarrollo, apenas el 30% de la producción agrícola se somete a procesos industriales. En los países de altos ingresos, el 98% se procesa. Esto sugiere que hay grandes oportunidades para los países en desarrollo en materia de agronegocios, por lo que fundamentados en esto se ve una gran oportunidad de mejora en los cultivos de Colombia para que sean más eficientes y de mayor calidad que aporten tanto al agricultor colombiano como a la economía, desarrollo, infraestructura y crecicmiento del pais.


# Interfaz gráfica

A continuación se muestran los mockups de la aplicación explicados uno a uno y según el hilo que debe llevar el usurio por la apliación.

LOGIN 

Si el usuario ya está registrado puede ingresar con su usuario y contraseña como credenciales propuestas por este mismo, de lo contrario deberá acceder a la opción registrate ahora.

[![Vista-1.jpg](https://i.postimg.cc/SKnRmkkd/Vista-1.png)](https://postimg.cc/Y4wM3c4L)

REGISTRARSE

La aplicación solicitará correo de ingreso y contraseña proporcionadas por el usuario, además de la confirmación de la misma. Una vez confirmada la contraseña podrá finalizar su proceso de registro; posteriormente será redirigido a la ventana login para iniciar sesión con las credenciales creadas anteriormente.

[![Vista-2.png](https://i.postimg.cc/X7D04FqM/Vista-2.png)](https://postimg.cc/1f6dK8DM)

VENTANA DE CARGA

Una vez el usuario esté logueado se visualizará la ventana de carga por aproximadamente tres segundos mientras cargan los datos de la aplicación, lo cual le indicará al usuario que el ingreso fué correcto.

[![Vista-3.jpg](https://i.postimg.cc/cLrtLQcf/Vista-3.png)](https://postimg.cc/RWxZPJy0)

HOME

En esta ventana podrá interactuar con el menú principal con cinco opciones diferentes.

[![Vista-4.jpg](https://i.postimg.cc/BZzRL3Tk/Vista-4.png)](https://postimg.cc/bdQmWKF9)

1) AGREGAR CULTIVO

Ésta opción lo redirigirá a una nueva ventana con un formulario para agregar algún cultivo donde se solicitará algunos datos de este.

[![vista-13.jpg](https://i.postimg.cc/xCZRp0gz/vista-13.jpg)](https://postimg.cc/CBjkzp3h)

2) VER CULTIVO

esta opción lo redirige a una nueva ventana donde el usuario podrá visualizar cada uno de sus cultivos y acceder a ellos independientemente, igualmente se visualiza una gráfica tipo anillo con los porcentajes de área de los cultivos. 

[![Vista-5.png](https://i.postimg.cc/N0PZ6whK/Vista-5.png)](https://postimg.cc/d7rWFpVF)

SELECCIONAR CULTIVO

Una vez seleccionado el cultivo se accee a otra ventana donde están las opciones: Informe general, horas trabajadas, hectáreas trabajadas y agua regada.

[![Vista-6.jpg](https://i.postimg.cc/y6hvBzf0/Vista-6.png)](https://postimg.cc/rzmSC72p)

- OPCIÓN INFORME GENERAL 

Una vez se seleccione esta ocpción se podra ver el balance de horas trabajadas por medio de una graficade barras y el clima de la región por medio de un mapa y algunos datos del clima

[![Vista-7.jpg](https://i.postimg.cc/xTMkB0tS/Vista-7.png)](https://postimg.cc/bsYNtf76)

[![Vista-8.jpg](https://i.postimg.cc/qvW5qx2K/Vista-8.png)](https://postimg.cc/hXbr3xy4)

- HORAS TRABAJADAS

Al seleccionar esta popción se mostrará un formulario donde se visualiza el tiempo trabajado y la fecha.

[![Vista-9.jpg](https://i.postimg.cc/VNyKmLcB/Vista-9.png)](https://postimg.cc/9DpdB20r)

- AGUA REGADA

En esta opción se visualizará un formulario con los campos: cantidad de metros cúbicos y fecha.

[![Vista-10.jpg](https://i.postimg.cc/G2KKFFTq/Vista-10.png)](https://postimg.cc/xJkLYbRz)

- HECTÁREAS TRABAJADAS 

Se visualizará un formulario con los campo: cantidad de hectáreas y fecha. 

[![Vista-11.jpg](https://i.postimg.cc/g0PH1Svg/Vista-11.png)](https://postimg.cc/ZWVNdLsN)

3) ACTIVIDADES

En esta opción se despliega un calendario para agregar actividades dependiendo de la fecha, al seleccionar el día se abrirá una nueva ventana para crear la actividad para registrar. 

[![Vista-13.jpg](https://i.postimg.cc/x8WzM856/Vista-13.png)](https://postimg.cc/MfmH8WhB)

[![Vista-14.jpg](https://i.postimg.cc/BvMPNMNL/Vista-14.png)](https://postimg.cc/sBG2gPks)

4) OPCIÓN PERFIL

En esta opción se accede a una nueva ventana donde se visualiza el correo del usuario y donde se permite cerrar sesión.

5) OPCIÓN TIPS SOBRE TUS CULTIVOS 

En esta opción se redirige a una nueva ventana, donde se encuentran cinco consejos para un buen cultivo.

[![Vista-12.jpg](https://i.postimg.cc/Qx9gCGm6/Vista-12.png)](https://postimg.cc/gnpLt7fR)



# Desarrollo Proyecto

Inicialmente a partir de los mockups se realizaron las primeras ventanas de registro y login por medio de la autenticación de Firebase, adicionalmente se usaron los servicios del Firestore Database para guardar los datos relacionados a cada cultivo, por medio de cada uno de los formularios. Además se hizo uso de la API de google maps y de otra API que tiene la información del clima dependiendo la región en donde se encuentre.

A continuación se muestra la aplicación en funcionamiento a modo de hilo recorriendo cada una de las ventanas y/o vistas que se habían propuesto.

[![1.jpg](https://i.postimg.cc/fTXgggFr/1.jpg)](https://postimg.cc/tYq5sD93)

[![2.jpg](https://i.postimg.cc/FsjDxkkt/2.jpg)](https://postimg.cc/1f3GmtKM)

[![3.jpg](https://i.postimg.cc/9ffbVx8V/3.jpg)](https://postimg.cc/qN9yxGHZ)

[![4.jpg](https://i.postimg.cc/bYnTzSTG/4.jpg)](https://postimg.cc/PNtZyx0h)


### Conclusiones 

Durante el desarrollo de esta aplicación afianzamos los conocimientos aprendidos en clase y fortalecimos nuestras habilidades de trabajo colaborativo gracias a las tecnologías.

Se logra poner el conocimiento al servicio de una problemática identificada en la sociedad e implementar un plan de acción donde se pasa por un periodo de investigación, ideación e implementación logrando aportar desde el campo de la tecnología a una solución sostenible.

Se logro evidenciar la practicidad del uso de Firebase para temas de autenticación de usuarios y almacenamiento de información.
