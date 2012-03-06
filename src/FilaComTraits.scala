import scala.collection.mutable.ArrayBuffer;

abstract class FilaInt {
  def get(): Int
  def put(x: Int)
  def size(): Int
}

class FilaIntSimples extends FilaInt {
  private val buf = new ArrayBuffer[Int];
  def get = buf.remove(0)
  def put(x: Int) { buf += x }
  def size = buf.length
}

trait Dobra extends FilaInt {
  abstract override def put(x: Int) { super.put(2 * x) }
}

trait Incrementa extends FilaInt {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtra extends FilaInt {
  abstract override def put(x: Int) { if (x > 0) super.put(x) }
}

///

class FilaDobroMaisUm extends FilaIntSimples with Incrementa with Dobra

object FilaComTrait {
  def main(args: Array[String]) {
    val fila1 = new FilaDobroMaisUm;
    fila1.put(1);
    fila1.put(2);
    println(fila1.get());
    println(fila1.get());

    val fila2 = new FilaIntSimples with Filtra
    fila2.put(-1);
    fila2.put(1);
    println(fila2.size());

  }
}



