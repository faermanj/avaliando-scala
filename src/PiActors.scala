import scala.util.Random
import Math._
import System._
import scala.actors.Actor
import scala.actors.Actor._

case object Calcule
case object Pare

class Calculador extends Actor {
  val rand = new Random
  var pi, in, cnt = 1.0

  def act() {
    while (true) {
      receive {
        case Calcule =>
          sender ! estimativaDePi
        case Pare => exit
      }
    }
  }

  def estimativaDePi: Double = {
    val x = rand.nextDouble - 0.5
    val y = rand.nextDouble - 0.5
    cnt += 1.0;
    if (sqrt(x * x + y * y) < 0.5) in += 1
    return in / cnt * 4
  }
}

class Coordenador(qtdCalculadores: Int) extends Actor {
  def act() {
    val inicio = currentTimeMillis
    var calculadores = List.fill(qtdCalculadores)(new Calculador)
    calculadores.foreach(c => {
        c.start
        c ! Calcule
    })
    while (true) {
      receive {
        case estimativa: Double =>
          val erro = abs(Pi - estimativa)
          if (erro > 0.0000001)
            sender ! Calcule
          else {
            val tempo = currentTimeMillis - inicio
            calculadores.foreach(_ ! Pare)                        
            println("Pi encontrado por " + sender + " = " + estimativa)
            println("Tempo de Execução: " + tempo)
            exit
          }
      }
    }
  }
}

object PiAtores {
  def main(args: Array[String]) {
    new Coordenador(2).start
  }
}