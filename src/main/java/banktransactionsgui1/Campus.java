package banktransactionsgui1;

/**
 Enum class representing a campus.
 Can be one of 3 campuses.
 @author Dany Chucri, Madhur Nutulapati
 */
public enum Campus {
        NEW_BRUNSWICK(0), // Enum representing the New Brunswick Campus
        NEWARK(1), // Enum representing the Newark Campus
        CAMDEN(2); // Enum representing the Camden Campus

        private final int value;

        Campus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
}