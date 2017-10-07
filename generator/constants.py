import math

F = len("Kanukova")
I = len("Sofia")
O = len("Alanovna")

SEED_1 = F * I  # A
SEED_2 = SEED_1 + 1  # B
EXPECTED_VALUE = F * O * 1000  # V
LEFT_BORDER = round(EXPECTED_VALUE / I)  # G
RIGHT_BORDER = EXPECTED_VALUE * 2 - LEFT_BORDER  # D
K = 3 + SEED_1 % 6  # YO
K_PLUS_1 = K + 1

NUMBER_OF_STEPS = 1000
STEP = round(((RIGHT_BORDER - LEFT_BORDER) / 1000), 1)

NUMBER_OF_VARIABLES = [10, 100, 1000, 5000, 10000, 20000]

EVEN_SKO = (RIGHT_BORDER - LEFT_BORDER) / (2 * math.sqrt(3))
EVEN_VARCOEF = EVEN_SKO/EXPECTED_VALUE

EXP_SKO = EXPECTED_VALUE
EXP_VARCOEF = 1

ERL_SKO_K = math.sqrt(K) * EXPECTED_VALUE / K
ERL_SKO_K_PLUS_1 = math.sqrt(K_PLUS_1) * EXPECTED_VALUE / K_PLUS_1

ERL_VARCOEF_K = math.sqrt(K) / K
ERL_VARCOEF_K_PLUS_1 = math.sqrt(K_PLUS_1) / K_PLUS_1

CSS_FILE = "<link rel='stylesheet' href='../../styles/style.css'>"

