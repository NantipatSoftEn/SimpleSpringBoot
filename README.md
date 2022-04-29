# บันทึกความเข้าใจ Spring Boot

## Architecture

design pattern: Clean Architecture Concept

- Model (Entity) ชุดรูปแบบ data (Object) - Business (Use case) เอา service มาใช้ตรงนี้แล้วส่งต่อให้ Api (Controller)
- Service layer ของ logic เล็กๆที่ทำเพียงหนึ่งหน้าที่จะได้ test ได้สะดวก
- Api (Controller) เอาใช้ business

## Install

- add module commom
- docker-compose up
- maven install
- add .env
- connect db with pgadmin `docker inspect <container id> | grep "IPAddress"`

## [Top Spring Annotation](https://medium.com/javarevisited/top-spring-annotations-4f691babe458)

- `@Bean`
  นี้หมายถึงสร้าง factory method เพื่อสร้าง instant ของ Spring bean, Spring boot เรียก method
  เหล่านี้เมื่อต้องการ instant of the return type is required.
  ชื่อของ bean จะเหมือนกับชื่อเมธอดของ factory โดยอัตโนมัติและเรายังสามารถตั้งชื่อแทนให้กับ bean
  > ดูตัวอย่างการใช้ `config/KafkaConfig/kafkaEmailTemplate` ที่สามารถเรียก instant โดยไม่ต้องผ่าน class
- `@Component` Service and Controller Both are different specializations of @Component

- `@Repository`
  This annotation represents the DAO (Data Access Object)

- `@Service`
  The service layer is where the application usually have the business logic to be reused and easily maintained

- `@Controller`
  This class is a Controller in Spring MVC (Model-View-Controller). The REST evolution of this annotation is the `@RestController` annotation.

- `@Configuration`
  บ่งบอกว่าคลาสประกาศเมธอด `@Bean` อย่างน้อยหนึ่งเมธอดและอาจถูกประมวลผลโดยคอนเทนเนอร์ Spring เพื่อสร้างนิยาม bean สำหรับ bean เหล่านั้นขณะรันไทม์

- `@Autowired`
  This annotation marks a dependency to be resolved and injected by Spring.
  ใช้กับ constructor หรือ setter

- `@Qualifier`
  ใช้ร่วมกับ `@Autowired` เพื่อแก้ไขสถานการณ์ที่คลุมเครือ เช่นหากเรามี `@Bean` 2 อันเราต้องแยกความแตกต่างโดยใช้ชื่อที่แตกต่างกัน

- `@Value`
  This annotation is used to inject property value into beans; can be used with constructor, setter and field injection as well

- `@Lazy`
  By default, Spring creates all singleton beans eagerly at the startup of the application context
  มีหลายกรณีที่เราจำเป็นต้องสร้าง bean เมื่อเราร้องขอไม่ใช่ที่การเริ่มต้นแอปพลิเคชัน คำอธิบายประกอบนี้สามารถวางไว้เหนือ:

- `@Primary`
  ถ้ามี `@Bean` หลายอันสามารถทำได้ด้วย `@Qualifier` แต่มีบางกรณีทั่วไปที่เราต้องใช้อินสแตนซ์หลักและไม่ค่อยเหมือนกับกรณีอื่น
  ในกรณีนี้เราสามารถทำเครื่องหมาย bean ที่ใช้บ่อยที่สุดด้วย @Primary และสิ่งนี้จะถูกเลือกไว้ข้างหน้า bean อื่น ๆ ของประเภทอินสแตนซ์เมื่อ Spring ต้องแก้ไขความคลุมเครือ

- `@Scope`
  Used to define the scope of a @Bean or a @Component. It can be singleton, prototype, request, session, globalSession or a custom scope.

## ท่าทีใช้บ่อย ๆ

- [ว่าด้วยเรื่อง “Optional” ใน Java](https://phayao.medium.com/%E0%B8%A7%E0%B9%88%E0%B8%B2%E0%B8%94%E0%B9%89%E0%B8%A7%E0%B8%A2%E0%B9%80%E0%B8%A3%E0%B8%B7%E0%B9%88%E0%B8%AD%E0%B8%87-optional-%E0%B9%83%E0%B8%99-java-4e24264f2c3d)
- SecurityContext ?
- HttpServletRequest

## เพิ่มเติม

- [Is Spring annotation @Controller same as @Service?](https://stackoverflow.com/questions/15922991/is-spring-annotation-controller-same-as-service)
- [Read Spring Beans in Depth](https://medium.com/javarevisited/spring-beans-in-depth-a6d8b31db8a1)
