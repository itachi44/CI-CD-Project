package sn.ept.git.seminaire.cicd.utils;

/**
 * @author ISENE
 */
public final class UrlMapping {

    public static final String BASE = "/api/";
    public static final String ENABLE = "/enable";
    public static final String DISABLE = "/disable";
    public static final String ID = "/{id}";

    private UrlMapping() {
        super();
    }


    public static final class Societe {

        private Societe() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "societes";
        public static final String ALL = Societe.BASE;
        public static final String FIND_BY_ID = Societe.BASE + ID;
        public static final String ADD = Societe.BASE;
        public static final String UPDATE = Societe.FIND_BY_ID;
        public static final String DELETE = Societe.FIND_BY_ID;
    }


    public static final class Site {

        private Site() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "sites";
        public static final String ALL = Site.BASE;
        public static final String FIND_BY_ID = Site.BASE + ID;
        public static final String ADD = Site.BASE;
        public static final String UPDATE = Site.FIND_BY_ID;
        public static final String DELETE = Site.FIND_BY_ID;
    }



    public static final class Agent {

        private Agent() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "agents";
        public static final String ALL = Agent.BASE;
        public static final String FIND_BY_ID = Agent.BASE + ID;
        public static final String ADD = Agent.BASE;
        public static final String UPDATE = Agent.FIND_BY_ID;
        public static final String DELETE = Agent.FIND_BY_ID;
    }

    public static final class Exercice {

        private Exercice() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "exercices";
        public static final String ALL = Exercice.BASE;
        public static final String FIND_BY_ID = Exercice.BASE + ID;
        public static final String ADD = Exercice.BASE;
        public static final String UPDATE = Exercice.FIND_BY_ID;
        public static final String DELETE = Exercice.FIND_BY_ID;
    }



    public static final class Tool {

        private Tool() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "tools";
        public static final String ALL = Tool.BASE;
        public static final String FIND_BY_ID = Tool.BASE + ID;
        public static final String ADD = Tool.BASE;
        public static final String UPDATE = Tool.FIND_BY_ID;
        public static final String DELETE = Tool.FIND_BY_ID;
    }


    public static final class Intervention {

        private Intervention() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "interventions";
        public static final String ALL = Intervention.BASE;
        public static final String FIND_BY_ID = Intervention.BASE + ID;
        public static final String ADD = Intervention.BASE;
        public static final String UPDATE = Intervention.FIND_BY_ID;
        public static final String DELETE = Intervention.FIND_BY_ID;
    }


    public static final class Type {

        private Type() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "types";
        public static final String ALL = Type.BASE;
        public static final String FIND_BY_ID = Type.BASE + ID;
        public static final String ADD = Type.BASE;
        public static final String UPDATE = Type.FIND_BY_ID;
        public static final String DELETE = Type.FIND_BY_ID;
    }


    public static final class Event {

        private Event() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "events";
        public static final String ALL = Event.BASE;
        public static final String FIND_BY_ID = Event.BASE + ID;
        public static final String ADD = Event.BASE;
        public static final String UPDATE = Event.FIND_BY_ID;
        public static final String DELETE = Event.FIND_BY_ID;
    }



    public static final class Attachement {

        private Attachement() {
            super();
        }

        public static final String BASE = UrlMapping.BASE + "attachements";
        public static final String ALL = Attachement.BASE;
        public static final String FIND_BY_ID = Attachement.BASE + ID;
        public static final String ADD = Attachement.BASE;
        public static final String UPDATE = Attachement.FIND_BY_ID;
        public static final String DELETE = Attachement.FIND_BY_ID;
    }

}