
class Moyenne {
  double notes = 0 ;
  double nombreDeNotes = 0 ;
  double meilleurNote = 0;
  int presence = 0;

  void ajouteNote (int note, double ponderation) {
    if(note>meilleurNote)
    {
      meilleurNote = note;
    }
    notes += ponderation*note;
    nombreDeNotes += ponderation;
  }

  void ajoutePresence(int coeffPresence){
    presence = coeffPresence;
  }

  double calculMoyenne() {
    return (((double)notes) / nombreDeNotes)*(presence*0.1) ;
  }

  double getMeilleurNote(){
    return meilleurNote;
  }

}
