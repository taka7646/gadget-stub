import play.api._

object Global extends GlobalSettings {

  override def onStart(app: Application){
    Logger.info("start app")
  }
  override def onStop(app: Application) {
    Logger.info("end app")
  }
}