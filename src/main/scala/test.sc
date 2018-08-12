case class A(a: String, b: Int)
val a = A("str",2)
a.productIterator.foreach(println)
a.productElement(1)

val b = Array(1,2,3)
val c =  Array("a","b","c")
b.zip(c)

Array(true, true, false).exists(b => !b)

getClass.getResource("test.json").getFile

Math.sqrt()