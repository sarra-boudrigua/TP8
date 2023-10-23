package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    private ArrayList<ServicePrevu> sp ;
    private ArrayList<Intervention> inter;

    ServicePrevu service;

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    public Enseignant(String nom, String email) {
        super(nom, email);
        this.inter = new ArrayList<Intervention>();
        this.sp = new ArrayList<ServicePrevu>();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        int equivalentTD = 0;
        for (ServicePrevu service : sp) {
            equivalentTD += (int) service.getVolumeCM() * 1.5;
            equivalentTD += (int) service.getVolumeTD();
            equivalentTD += (int) service.getVolumeTP() * 0.75;
        }
        return equivalentTD;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        int equivalentTD = 0;
        for (ServicePrevu service : sp) {
            if (service.getUe() == ue) {
                equivalentTD += (int) service.getVolumeCM() * 1.5;
                equivalentTD += (int) service.getVolumeTD();
                equivalentTD += (int) service.getVolumeTP() * 0.75;
            }
        }
        return equivalentTD;
    }
    public boolean enSousService() {
        return heuresPrevues() < 192;
    }


    public int resteAPlanifier(UE ue, TypeIntervention type) {
        if (192 - heuresPrevues() > 0) {
            return 192 - heuresPrevues();
        } else {
            return 0;
        }
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        if(service == null){
            service = new ServicePrevu(volumeCM, volumeTD, volumeTP, ue);
            sp.add(service);
        } else {
            service.setVolumeCM(service.getVolumeCM()+ volumeCM);
            service.setVolumeTD(service.getVolumeTD()+ volumeTD);
            service.setVolumeTP(service.getVolumeTP()+ volumeTP);
        }
    }

    public void ajouterIntervention(Intervention i) {
        inter.add(i);
    }

}