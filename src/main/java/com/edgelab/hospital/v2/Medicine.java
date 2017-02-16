package com.edgelab.hospital.v2;

/**
 * Created by Chaklader on 2/16/17.
 */
interface  Medicine {

    Medicine None = new Medicine() {
        @Override
        public void on(QuarantineTwo q) {
            q.diabetics().becomes(q.death());
        }

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
        @Override
        public void on(QuarantineTwo q) {
            q.feverish().becomes(q.healthy());
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
        @Override
        public void on(QuarantineTwo q) {
            q.tuberculous().becomes(q.healthy());
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
            if (isHotMix(q.getTreatment())) {
                q.healthy().becomes(q.feverish());
            } else {
                // Prevent None effects, done is this.combine
            }
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.remove(Medicine.None)
                    .plus(this);
        }

        private boolean isHotMix(Treatment treatment) {
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
            if (isToxicMix(quarantineTwo.getTreatment())) {
                quarantineTwo.feverish().becomes(quarantineTwo.death());
                quarantineTwo.healthy().becomes(quarantineTwo.death());
                quarantineTwo.diabetics().becomes(quarantineTwo.death());
                quarantineTwo.tuberculous().becomes(quarantineTwo.death());
            } else{
                quarantineTwo.feverish().becomes(quarantineTwo.healthy());
            }
        }

        private boolean isToxicMix(Treatment treatment) {
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
