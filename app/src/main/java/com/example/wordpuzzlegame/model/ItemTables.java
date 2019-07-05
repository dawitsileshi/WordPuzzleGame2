package com.example.wordpuzzlegame.model;

public class ItemTables {

//    public static final String TABLE_AMHARIC = "amharic_table";
//    public static final String AMHARIC_ID = "amharic_id";
//    public static final String AMHARIC_WORD = "amharic_word";
////    public static final String AMHARIC_DEFINITION = "amharic_definition";
//    public static final String AMHARIC_EXAMPLE = "amharic_sentence";
//
//    public static final String CREATE_AMHARIC_TABLE = "CREATE TABLE " + TABLE_AMHARIC + "( " +
//            AMHARIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AMHARIC_WORD + " TEXT NOT NULL, "
//            + AMHARIC_EXAMPLE + " TEXT);";
//
//    public static final String DROP_AMHARIC_TABLE = "DROP TABLE IF EXISTS " + TABLE_AMHARIC;
//
//    public static final String TABLE_TIGIRIGNA = "tigrigna_table";
//    public static final String TIGIRIGNA_ID = "tigrigna_id";
//    public static final String TIGIRIGNA_WORD = "tigrigna_word";
////    public static final String TIGIRIGNA_DEFINITION = "tigirigna_definition";
//    public static final String TIGIRIGNA_EXAMPLE = "tigrigna_sentence";
//
//    public static final String CREATE_TIGIRIGNA_TABLE = "CREATE TABLE " + TABLE_TIGIRIGNA + "( " +
//            TIGIRIGNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIGIRIGNA_WORD + " TEXT NOT NULL, "
//            + TIGIRIGNA_EXAMPLE+ " TEXT);";
//
//    public static final String DROP_TIGIRIGNA_TABLE = "DROP TABLE IF EXISTS " + TABLE_TIGIRIGNA;
//
//    public static final String TABLE_ENGLISH= "english_table";
//    public static final String ENGLISH_ID = "english_id";
//    public static final String ENGLISH_WORD = "english_word";
////    public static final String ENGLISH_DEFINITION = "english_definition";
//    public static final String ENGLISH_EXAMPLE = "english_sentence";
//
//    public static final String CREATE_ENGLISH_TABLE = "CREATE TABLE " + TABLE_ENGLISH + "( " +
//            ENGLISH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ENGLISH_WORD + " TEXT NOT NULL, "
//            + ENGLISH_EXAMPLE + " TEXT);";
//
//    public static final String DROP_ENGLISH_TABLE = "DROP TABLE IF EXISTS " + TABLE_ENGLISH;
//
//    public static final String TABLE_RELATIONSHIP = "relationship_table";
//    public static final String RELATIONSHIP_ID = "relationship_id";
//    public static final String ID_ENGLISH = "english_id";
//    public static final String ID_AMHARIC = "amharic_id";
//    public static final String ID_TIGIRIGNA = "tigrigna_id";
//    public static final String IMAGE = "relationship_image";
//
//    public static final String CREATE_RELATIONSHIP_TABLE = "CREATE TABLE " + TABLE_RELATIONSHIP+ "( " +
//            RELATIONSHIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ID_ENGLISH + " INTEGER, "
//            + ID_AMHARIC + " INTEGER, " + ID_TIGIRIGNA + " INTEGER);";


    public static final String LANGUAGE_TABLE = "languages";
    public static final String LANG_ID = "lang_id";
    public static final String LANGUAGE = "lang";
    public static final String ALPHABETS = "alphabets";

    public static final String WORD_TABLE = "words";
    public static final String WORD_ID = "word_id";
    public static final String WORD = "word";
    public static final String WORD_CODE = "word_code";

    public static final String ASSETS_TABLE = "assets";
    public static final String ASSETS_ID = "assets_id";
    public static final String IMAGE = "image";

    public static final String RESULTS_TABLE = "result";
    public static final String RESULT_ID = "result_id";
    public static final String QUIZ_TYPE = "game_type";
    public static final String SCORE = "score";
    public static final String TOTAL = "total";
    public static final String ANSWERED_WORD = "answered_word";
    public static final String UNANSWERED_WORD = "unanswered_word";
    public static final String DAY = "day";
    public static final String CORRECT_INCORRECT = "correct_incorrect";

    public static final String STRUGGLING_WORD_TABLE = "struggling_words";
    public static final String STRUGGLING_WORD_ID = "struggling_word_id";
    public static final String WRONGLY_ANSWERED_TIMES = "wrongly_answered_times";
    public static final String RIGHTLY_ANSWERED_TIMES = "rightly_answered_times";

    public static final String KID_TABLE = "kid";
    public static final String KID_ID = "kid_id";
    public static final String KID_NAME = "kid_name";
    public static final String KID_GENDER = "kid_gender";
    public static final String KID_GRADE = "kid_grade";

    public static final String PARENT_TABLE = "parent";
    public static final String PARENT_ID = "parent_id";
    public static final String PARENT_NAME = "parent_name";
    public static final String PARENT_GENDER = "parent_gender";
    public static final String PARENT_RELATION = "parent_relation";
    public static final String PARENT_EMAIL = "parent_email";
    public static final String PARENT_PASSWORD = "parent_password";

    public static final String ANSWER_TABLE = "answer";
    public static final String ANSWER_ID = "answer_id";
    public static final String STRUGGLING = "struggling";

    public static final String REWARD_TABLE = "reward";
    public static final String REWARD_ID = "reward_id";
    public static final String HEART_VALUE = "heart";
    public static final String SECONDS_LEFT = "seconds_left";
}
