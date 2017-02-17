package com.edgelab.hospital.v2;

/**
 * Created by Chaklader on 2/16/17.
 */
interface Medicine {

    Medicine None = new Medicine() {
        /**
         * medicine will be provided to the quarantine system
         *
         * @param q represents the quarantine system where the treatment wil be provided
         */
        @Override
        public void on(QuarantineTwo q) {
            q.diabetics().changeHealthStatus(q.death());
        }


        /**
         * add with other medicine to make the combined treatment procedure
         *
         * @param treatment currect treatment scheme before adding this medicine
         * @return
         */
        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }

        @Override
        public String toString() {
            return "None";
        }
    };


    Medicine Aspirin = new Medicine() {
        /**
         * Aspirine cures the fever
         *
         * @param q
         */
        @Override
        public void on(QuarantineTwo q) {
            q.feverish().changeHealthStatus(q.healthy());
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }

        @Override
        public String toString() {
            return "Aspirin";
        }
    };


    Medicine Antibiotic = new Medicine() {

        /**
         * Antibiotic cures the tuberculous
         *
         * @param q
         */
        @Override
        public void on(QuarantineTwo q) {
            q.tuberculous().changeHealthStatus(q.healthy());
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }

        @Override
        public String toString() {
            return "Antibiotic";
        }
    };

    Medicine Insulin = new Medicine() {

        @Override
        public void on(QuarantineTwo q) {

            if (isInsulinCombinedWithAntibiotic(q.getTreatment())) {
                q.healthy().changeHealthStatus(q.feverish());
            } else {
                // Prevent None effects, done is this.combine
            }
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.remove(Medicine.None)
                    .plus(this);
        }

        /**
         * helper method to see whether the Insulin is combined with Antibiotic
         *
         * @param treatment
         * @return
         */
        private boolean isInsulinCombinedWithAntibiotic(Treatment treatment) {
            return treatment.contains(this) &&
                    treatment.contains(Medicine.Antibiotic);
        }

        @Override
        public String toString() {
            return "Insulin";
        }
    };


    Medicine Paracetamol = new Medicine() {

        @Override
        public void on(QuarantineTwo quarantineTwo) {
            if (isParacetamolIsCombinedWithAspirin(quarantineTwo.getTreatment())) {

                /* the patient will die irrespective of the health condition*/
                quarantineTwo.feverish().changeHealthStatus(quarantineTwo.death());
                quarantineTwo.healthy().changeHealthStatus(quarantineTwo.death());
                quarantineTwo.diabetics().changeHealthStatus(quarantineTwo.death());
                quarantineTwo.tuberculous().changeHealthStatus(quarantineTwo.death());
            }

            /*if we only use Paracetamol, the fever will be cured*/
            else {
                quarantineTwo.feverish().changeHealthStatus(quarantineTwo.healthy());
            }
        }

        private boolean isParacetamolIsCombinedWithAspirin(Treatment treatment) {
            return treatment.contains(this) &&
                    treatment.contains(Aspirin);
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }
    };


    void on(QuarantineTwo quarantineTwo);
    Treatment combine(Treatment treatment);
}
