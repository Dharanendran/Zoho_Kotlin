package practise

import kotlin.Int

//fun demo.main() {
//    var lambda:(e1:Int ,e2:Int)->Int = {e1,e2->e1+e2}
//    higherorder(5,2,c1()::f1)
//}
//fun higherorder(a:Int, b:Int, func: KFunction2<Int, Int, Int>){
//    func(a,b)
//}
//fun dummy(a:Int , b:Int):Int{
//    print("function from dummy")
//    return 1
//}
//class c1{
//    fun f1(a:Int ,b:Int):Int{ print("from c1")
//        return a+b }
//}
//fun demo.main(){
//     var s:String? = "sxsa".also { println(it.length) }
//     var a =s?:1
//    print(a)
//}

//fun demo.main()
//{
//    var a = arrayListOf(1,2,3,4,14,)
//    var b = Myclass<Int>(5 , a)
//
//    var c = ArrayList<String>()
//    b.forEach {ele ->
//        run {
//           c.add(ele.toString())
//        }
//    }
//
//    println(b.map { ele -> ele.toString() + "abc" })
//    println("answer ${b.filter { ele ->if(ele.toString().length > 1) ele.toString() else ""}}")
//    println(b[1])
//    println(b.myMethod())
//}
//
//class Myclass<E>(size1:Int ,  var l1 :ArrayList<E>)
//{
//    var size:Int = size1
//    var l:ArrayList<E> = assign()
//    var  arr : ArrayList<E>? = null
//
//    fun  assign():ArrayList<E>{
//        var dummy = ArrayList<E>()
//        for(i in size-1 downTo 0)
//            this.l1[i]?.apply { dummy.add(this) }
//        return  dummy
//    }
//
//    fun myMethod():ArrayList<E>
//    {
//        var dummyList = ArrayList<E>()
//        for(i in size-1 downTo 0){
//            l?.get(i)?.let { dummyList.add(it) }
//        }
//        return dummyList
//    }
//
//    fun map(f:(ele:E)->String):ArrayList<String> {
//        var ref = ArrayList<String>()
//        for (ele in l) {
//            ref.add(f(ele))
//        }
//        return ref
//    }
//
//    fun filter(func:(ele:E)->String):ArrayList<String>{
//        var ref = ArrayList<String>()
//        for (i in l){
//            if (func(i) != "")
//                ref.add(i.toString()) }
//        return ref
//    }
//
//    fun forEach(x : (E) -> Unit){
//        for(i in l){
//            x(i)
//        }
//    }
//
//    operator fun get(ind:Int):E?{
//        var v : Int = 0
//        for(i in l!!){
//           if(v==ind) return i
//            v++
//        }
//        return null
//    }
//    override fun toString(): String {
//        return l.toString()
//    }
//}
//
//
//interface  myInterface<E>{
//    fun mymethod1():myInterface<E>
//    fun mymethod2():myInterface<E>
//}
//
//
//
//class A<E>(var arr:ArrayList<E>): myInterface<E>
//{
//    override fun mymethod1():myInterface<E>{
//        return this
//    }
//    override fun  mymethod2():myInterface<E>{
//        return this
//    }
//    fun my_method3() {}
//}
//
//
//class B(var arr:ArrayList<E>): myInterface<E>{
//
//    override fun mymethod1():myInterface<E>{
//        return this
//    }
//    override fun  mymethod2():myInterface<E>{
//        return this
//    }
//
//    fun my_method3() {
//
//    }
//}
//
//
//
//fun main()
//{
//    var arr1 = ArrayList<myInterface<E>>()
//    var arr  = ArrayList<String>().apply {
//        add("hhh")
//        add("jhbhj")
//        add("jhh")
//    }
//    var arr2 = ArrayList<Int>().apply{
//        add(1)
//        add(2)
//        add(3)
//    }
//    var a :myInterface<String> = A(arr)
//    var b :myInterface<Int> = B(arr2)
//    arr1.add(a)
//    arr1.add(b)
//    println(a)
//    getInterface(arr1)
//}
//
//fun getInterface(a:ArrayList<myInterface<E>>) {
//    a[i].mymethod1()
//}









//fun main(){
//    var single = Q
//    println(Q.name)
//    println(A.cobj)
//    print(A())
//    var a = A.getObj()?.also{
//        print(it.name)
//    }
//    var b = A.getObj()?.also{
//        print(it.name)
//    }
//}
//
//class A constructor(){
//    var name:String = "abc"
//    companion object cobj{
//        private var checkInstance:Boolean = false
//        var age = 1
//        var gender = "male"
//        fun getObj(): A? {
//            if (!checkInstance) {
//                checkInstance = true
//                return A()
//            }
//            return null
//        }
//    }
//}
//object Q{
//    var name :String = "dharani"
//}
//class A{
//    var name :String = "fdfed"
//}

fun main(){
    var a=1
    var b=a
    a++

    println(b)

}
//fun getMethod(arr:MutableList<A>):A{
//    return arr[1]
//}






