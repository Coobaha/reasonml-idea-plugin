package com.reason.lang.reason;

import com.reason.lang.core.psi.type.MlCompositeElementType;
import com.reason.lang.core.psi.type.MlTokenElementType;
import com.reason.lang.core.psi.type.MlTypes;
import com.reason.lang.core.stub.type.*;

public class RmlTypes extends MlTypes {

    public static final RmlTypes INSTANCE = new RmlTypes();

    private RmlTypes() {
        // Composite element types

        EXTERNAL_STMT = new PsiExternalStubElementType("EXTERNAL_STMT", RmlLanguage.INSTANCE);
        LET_STMT = new PsiLetStubElementType("LET_STMT", RmlLanguage.INSTANCE);
        MODULE_STMT = new PsiModuleStubElementType("MODULE_STMT", RmlLanguage.INSTANCE);
        EXP_TYPE = new PsiTypeStubElementType("EXP_TYPE", RmlLanguage.INSTANCE);
        VAL_EXPR = new PsiValStubElementType("VAL_EXPRESSION", RmlLanguage.INSTANCE);
        ANNOTATION_EXPR = new MlCompositeElementType("ANNOTATION_EXPRESSION", RmlLanguage.INSTANCE);
        EXCEPTION_EXPR = new MlCompositeElementType("EXCEPTION_EXPRESSION", RmlLanguage.INSTANCE);
        INCLUDE_STMT = new MlCompositeElementType("INCLUDE_STMT", RmlLanguage.INSTANCE);
        MACRO_NAME = new MlCompositeElementType("MACRO_NAME", RmlLanguage.INSTANCE);
        MODULE_PATH = new MlCompositeElementType("MODULE_PATH", RmlLanguage.INSTANCE);
        OPEN_STMT = new MlCompositeElementType("OPEN_STMT", RmlLanguage.INSTANCE);
        ASSERT_STMT = new MlCompositeElementType("ASSERT_STMT", RmlLanguage.INSTANCE);
        SCOPED_EXPR = new MlCompositeElementType("SCOPED_EXPR", RmlLanguage.INSTANCE);
        VARIANT_EXP = new MlCompositeElementType("VARIANT_EXP", RmlLanguage.INSTANCE);
        FUN_EXPR = new MlCompositeElementType("FUN_EXPR", RmlLanguage.INSTANCE);
        FUN_PARAMS = new MlCompositeElementType("FUN_PARAMS", RmlLanguage.INSTANCE);
        FUN_BODY = new MlCompositeElementType("FUN_BODY", RmlLanguage.INSTANCE);
        LET_BINDING = new MlCompositeElementType("LET_BINDING", RmlLanguage.INSTANCE);
        TYPE_CONSTR_NAME = new MlCompositeElementType("TYPE_CONSTR_NAME", RmlLanguage.INSTANCE);
        TYPE_BINDING = new MlCompositeElementType("TYPE_BINDING", RmlLanguage.INSTANCE);
        PATTERN_MATCH_EXPR = new MlCompositeElementType("PATTERN_MATCH_EXPR", RmlLanguage.INSTANCE);
        SIG_SCOPE = new MlCompositeElementType("SIG_SCOPE", RmlLanguage.INSTANCE);
        NAMED_SYMBOL = new MlCompositeElementType("NAMED_SYMBOL", RmlLanguage.INSTANCE);
        IF_STMT = new MlCompositeElementType("IF_STMT", RmlLanguage.INSTANCE);
        BIN_CONDITION = new MlCompositeElementType("BIN_CONDITION", RmlLanguage.INSTANCE);
        SWITCH_EXPR = new MlCompositeElementType("SWITCH_EXPR", RmlLanguage.INSTANCE);
        TAG_START = new MlCompositeElementType("TAG_START", RmlLanguage.INSTANCE);
        TAG_CLOSE = new MlCompositeElementType("TAG_CLOSE", RmlLanguage.INSTANCE);
        TAG_PROPERTY = new MlCompositeElementType("TAG_PROPERTY", RmlLanguage.INSTANCE);
        RECORD_EXPR = new MlCompositeElementType("RECORD_EXPR", RmlLanguage.INSTANCE);
        RECORD_FIELD = new MlCompositeElementType("RECORD_FIELD", RmlLanguage.INSTANCE);
        INTERPOLATION_EXPR = new MlCompositeElementType("INTERPOLATION_EXPR", RmlLanguage.INSTANCE);
        TRY_EXPR = new MlCompositeElementType("TRY_EXPR", RmlLanguage.INSTANCE);
        WITH_EXPR = new MlCompositeElementType("WITH_EXPR", RmlLanguage.INSTANCE);

        // Token element types

        BOOL = new MlTokenElementType("BOOL", RmlLanguage.INSTANCE);
        STRING = new MlTokenElementType("STRING", RmlLanguage.INSTANCE);
        FLOAT = new MlTokenElementType("FLOAT", RmlLanguage.INSTANCE);
        CHAR = new MlTokenElementType("CHAR", RmlLanguage.INSTANCE);
        INT = new MlTokenElementType("INT", RmlLanguage.INSTANCE);

        BOOL_VALUE = new MlTokenElementType("BOOL_VALUE", RmlLanguage.INSTANCE);
        STRING_VALUE = new MlTokenElementType("STRING_VALUE", RmlLanguage.INSTANCE);
        FLOAT_VALUE = new MlTokenElementType("FLOAT_VALUE", RmlLanguage.INSTANCE);
        CHAR_VALUE = new MlTokenElementType("CHAR_VALUE", RmlLanguage.INSTANCE);
        INT_VALUE = new MlTokenElementType("INT_VALUE", RmlLanguage.INSTANCE);
        EXCEPTION_NAME = new MlTokenElementType("EXCEPTION_NAME", RmlLanguage.INSTANCE);
        PROPERTY_NAME = new MlTokenElementType("PROPERTY_NAME", RmlLanguage.INSTANCE);
        GENERIC_COND = new MlTokenElementType("GENERIC_COND", RmlLanguage.INSTANCE);
        SWITCH = new MlTokenElementType("SWITCH", RmlLanguage.INSTANCE);
        FUNCTION = new MlTokenElementType("FUNCTION", RmlLanguage.INSTANCE);
        FUN = new MlTokenElementType("FUN", RmlLanguage.INSTANCE);
        FUNCTOR = new MlTokenElementType("FUNCTOR", RmlLanguage.INSTANCE);
        UPPER_SYMBOL = new MlTokenElementType("UPPER_SYMBOL", RmlLanguage.INSTANCE);
        IF = new MlTokenElementType("IF", RmlLanguage.INSTANCE);
        LOCAL_OPEN = new MlTokenElementType("LOCAL_OPEN", RmlLanguage.INSTANCE);
        AND = new MlTokenElementType("AND", RmlLanguage.INSTANCE);
        ANDAND = new MlTokenElementType("ANDAND", RmlLanguage.INSTANCE);
        ARROBASE = new MlTokenElementType("ARROBASE", RmlLanguage.INSTANCE);
        ARROW = new MlTokenElementType("ARROW", RmlLanguage.INSTANCE);
        ASSERT = new MlTokenElementType("ASSERT", RmlLanguage.INSTANCE);
        AS = new MlTokenElementType("AS", RmlLanguage.INSTANCE);
        BACKTICK = new MlTokenElementType("BACKTICK", RmlLanguage.INSTANCE);
        BEGIN = new MlTokenElementType("BEGIN", RmlLanguage.INSTANCE);
        CARRET = new MlTokenElementType("CARRET", RmlLanguage.INSTANCE);
        COLON = new MlTokenElementType("COLON", RmlLanguage.INSTANCE);
        COMMA = new MlTokenElementType("COMMA", RmlLanguage.INSTANCE);
        COMMENT = new MlTokenElementType("COMMENT", RmlLanguage.INSTANCE);
        DIFF = new MlTokenElementType("DIFF", RmlLanguage.INSTANCE);
        LT_OR_EQUAL = new MlTokenElementType("LT_OR_EQUAL", RmlLanguage.INSTANCE);
        GT_OR_EQUAL = new MlTokenElementType("GT_OR_EQUAL", RmlLanguage.INSTANCE);
        DOLLAR = new MlTokenElementType("DOLLAR", RmlLanguage.INSTANCE);
        DOT = new MlTokenElementType("DOT", RmlLanguage.INSTANCE);
        DOTDOTDOT = new MlTokenElementType("DOTDOTDOT", RmlLanguage.INSTANCE);
        DO = new MlTokenElementType("DO", RmlLanguage.INSTANCE);
        DONE = new MlTokenElementType("DONE", RmlLanguage.INSTANCE);
        ELSE = new MlTokenElementType("ELSE", RmlLanguage.INSTANCE);
        END = new MlTokenElementType("END", RmlLanguage.INSTANCE);
        NOT_EQ = new MlTokenElementType("EQ", RmlLanguage.INSTANCE);
        NOT_EQEQ = new MlTokenElementType("EQEQ", RmlLanguage.INSTANCE);
        EQ = new MlTokenElementType("EQ", RmlLanguage.INSTANCE);
        EQEQ = new MlTokenElementType("EQEQ", RmlLanguage.INSTANCE);
        EQEQEQ = new MlTokenElementType("EQEQEQ", RmlLanguage.INSTANCE);
        EXCEPTION = new MlTokenElementType("EXCEPTION", RmlLanguage.INSTANCE);
        EXCLAMATION_MARK = new MlTokenElementType("EXCLAMATION_MARK", RmlLanguage.INSTANCE);
        EXTERNAL = new MlTokenElementType("EXTERNAL", RmlLanguage.INSTANCE);
        FOR = new MlTokenElementType("FOR", RmlLanguage.INSTANCE);
        TYPE_ARGUMENT = new MlTokenElementType("TYPE_ARGUMENT", RmlLanguage.INSTANCE);
        GT = new MlTokenElementType("GT", RmlLanguage.INSTANCE);
        IN = new MlTokenElementType("IN", RmlLanguage.INSTANCE);
        LAZY = new MlTokenElementType("LAZY", RmlLanguage.INSTANCE);
        INCLUDE = new MlTokenElementType("INCLUDE", RmlLanguage.INSTANCE);
        LARRAY = new MlTokenElementType("LARRAY", RmlLanguage.INSTANCE);
        LBRACE = new MlTokenElementType("LBRACE", RmlLanguage.INSTANCE);
        LBRACKET = new MlTokenElementType("LBRACKET", RmlLanguage.INSTANCE);
        LET = new MlTokenElementType("LET", RmlLanguage.INSTANCE);
        LIDENT = new MlTokenElementType("LIDENT", RmlLanguage.INSTANCE);
        LIST = new MlTokenElementType("LIST", RmlLanguage.INSTANCE);
        LPAREN = new MlTokenElementType("LPAREN", RmlLanguage.INSTANCE);
        LT = new MlTokenElementType("LT", RmlLanguage.INSTANCE);
        MATCH = new MlTokenElementType("MATCH", RmlLanguage.INSTANCE);
        MINUS = new MlTokenElementType("MINUS", RmlLanguage.INSTANCE);
        MINUSDOT = new MlTokenElementType("MINUSDOT", RmlLanguage.INSTANCE);
        MODULE = new MlTokenElementType("MODULE", RmlLanguage.INSTANCE);
        MUTABLE = new MlTokenElementType("MUTABLE", RmlLanguage.INSTANCE);
        NONE = new MlTokenElementType("NONE", RmlLanguage.INSTANCE);
        OF = new MlTokenElementType("OF", RmlLanguage.INSTANCE);
        OPEN = new MlTokenElementType("OPEN", RmlLanguage.INSTANCE);
        OPTION = new MlTokenElementType("OPTION", RmlLanguage.INSTANCE);
        POLY_VARIANT = new MlTokenElementType("POLY_VARIANT", RmlLanguage.INSTANCE);
        VARIANT_NAME = new MlTokenElementType("VARIANT_NAME", RmlLanguage.INSTANCE);
        PIPE = new MlTokenElementType("PIPE", RmlLanguage.INSTANCE);
        PIPE_FORWARD = new MlTokenElementType("PIPE_FORWARD", RmlLanguage.INSTANCE);
        PIPE_FIRST = new MlTokenElementType("PIPE_FIRST", RmlLanguage.INSTANCE);
        PLUS = new MlTokenElementType("PLUS", RmlLanguage.INSTANCE);
        PERCENT = new MlTokenElementType("PERCENT", RmlLanguage.INSTANCE);
        PLUSDOT = new MlTokenElementType("PLUSDOT", RmlLanguage.INSTANCE);
        QUESTION_MARK = new MlTokenElementType("QUESTION_MARK", RmlLanguage.INSTANCE);
        QUOTE = new MlTokenElementType("QUOTE", RmlLanguage.INSTANCE);
        RAISE = new MlTokenElementType("RAISE", RmlLanguage.INSTANCE);
        RARRAY = new MlTokenElementType("RARRAY", RmlLanguage.INSTANCE);
        RBRACE = new MlTokenElementType("RBRACE", RmlLanguage.INSTANCE);
        RBRACKET = new MlTokenElementType("RBRACKET", RmlLanguage.INSTANCE);
        REC = new MlTokenElementType("REC", RmlLanguage.INSTANCE);
        REF = new MlTokenElementType("REF", RmlLanguage.INSTANCE);
        RPAREN = new MlTokenElementType("RPAREN", RmlLanguage.INSTANCE);
        SEMI = new MlTokenElementType("SEMI", RmlLanguage.INSTANCE);
        SIG = new MlTokenElementType("SIG", RmlLanguage.INSTANCE);
        SHARP = new MlTokenElementType("SHARP", RmlLanguage.INSTANCE);
        SHARPSHARP = new MlTokenElementType("SHARPSHARP", RmlLanguage.INSTANCE);
        SHORTCUT = new MlTokenElementType("SHORTCUT", RmlLanguage.INSTANCE);
        SLASH = new MlTokenElementType("SLASH", RmlLanguage.INSTANCE);
        SLASHDOT = new MlTokenElementType("SLASHDOT", RmlLanguage.INSTANCE);
        SOME = new MlTokenElementType("SOME", RmlLanguage.INSTANCE);
        STAR = new MlTokenElementType("STAR", RmlLanguage.INSTANCE);
        STARDOT = new MlTokenElementType("STARDOT", RmlLanguage.INSTANCE);
        STRUCT = new MlTokenElementType("STRUCT", RmlLanguage.INSTANCE);
        TAG_AUTO_CLOSE = new MlTokenElementType("TAG_AUTO_CLOSE", RmlLanguage.INSTANCE);
        TAG_NAME = new MlTokenElementType("TAG_NAME", RmlLanguage.INSTANCE);
        TAG_LT = new MlTokenElementType("TAG_LT", RmlLanguage.INSTANCE);
        TAG_LT_SLASH = new MlTokenElementType("TAG_LT_SLASH", RmlLanguage.INSTANCE);
        TAG_GT = new MlTokenElementType("TAG_GT", RmlLanguage.INSTANCE);
        TILDE = new MlTokenElementType("TILDE", RmlLanguage.INSTANCE);
        TO = new MlTokenElementType("TO", RmlLanguage.INSTANCE);
        THEN = new MlTokenElementType("THEN", RmlLanguage.INSTANCE);
        TRY = new MlTokenElementType("TRY", RmlLanguage.INSTANCE);
        TYPE = new MlTokenElementType("TYPE", RmlLanguage.INSTANCE);
        UIDENT = new MlTokenElementType("UIDENT", RmlLanguage.INSTANCE);
        UNIT = new MlTokenElementType("UNIT", RmlLanguage.INSTANCE);
        VAL = new MlTokenElementType("VAL", RmlLanguage.INSTANCE);
        PUB = new MlTokenElementType("PUB", RmlLanguage.INSTANCE);
        LOWER_SYMBOL = new MlTokenElementType("LOWER_SYMBOL", RmlLanguage.INSTANCE);
        WHEN = new MlTokenElementType("WHEN", RmlLanguage.INSTANCE);
        WHILE = new MlTokenElementType("WHILE", RmlLanguage.INSTANCE);
        WITH = new MlTokenElementType("WITH", RmlLanguage.INSTANCE);

        ASR = new MlTokenElementType("ASR", RmlLanguage.INSTANCE);
        CLASS = new MlTokenElementType("CLASS", RmlLanguage.INSTANCE);
        CONSTRAINT = new MlTokenElementType("CONSTRAINT", RmlLanguage.INSTANCE);
        DOWNTO = new MlTokenElementType("DOWNTO", RmlLanguage.INSTANCE);
        INHERIT = new MlTokenElementType("INHERIT", RmlLanguage.INSTANCE);
        INITIALIZER = new MlTokenElementType("INITIALIZER", RmlLanguage.INSTANCE);
        LAND = new MlTokenElementType("LAND", RmlLanguage.INSTANCE);
        LOR = new MlTokenElementType("LOR", RmlLanguage.INSTANCE);
        LSL = new MlTokenElementType("LSL", RmlLanguage.INSTANCE);
        LSR = new MlTokenElementType("LSR", RmlLanguage.INSTANCE);
        LXOR = new MlTokenElementType("LXOR", RmlLanguage.INSTANCE);
        METHOD = new MlTokenElementType("METHOD", RmlLanguage.INSTANCE);
        MOD = new MlTokenElementType("MOD", RmlLanguage.INSTANCE);
        NEW = new MlTokenElementType("NEW", RmlLanguage.INSTANCE);
        NONREC = new MlTokenElementType("NONREC", RmlLanguage.INSTANCE);
        OR = new MlTokenElementType("OR", RmlLanguage.INSTANCE);
        PRIVATE = new MlTokenElementType("PRIVATE", RmlLanguage.INSTANCE);
        VIRTUAL = new MlTokenElementType("VIRTUAL", RmlLanguage.INSTANCE);

        COLON_EQ = new MlTokenElementType("COLON_EQ", RmlLanguage.INSTANCE);
        COLON_GT = new MlTokenElementType("COLON_GT", RmlLanguage.INSTANCE);
        DOTDOT = new MlTokenElementType("DOTDOT", RmlLanguage.INSTANCE);
        SEMISEMI = new MlTokenElementType("SEMISEMI", RmlLanguage.INSTANCE);
        GT_BRACKET = new MlTokenElementType("GT_BRACKET", RmlLanguage.INSTANCE);
        GT_BRACE = new MlTokenElementType("GT_BRACE", RmlLanguage.INSTANCE);
        LEFT_ARROW = new MlTokenElementType("LEFT_ARROW", RmlLanguage.INSTANCE);
        RIGHT_ARROW = new MlTokenElementType("RIGHT_ARROW", RmlLanguage.INSTANCE);

        OBJECT = new MlTokenElementType("OBJECT", RmlLanguage.INSTANCE);
        RECORD = new MlTokenElementType("RECORD", RmlLanguage.INSTANCE);

        AMPERSAND = new MlTokenElementType("AMPERSAND", RmlLanguage.INSTANCE);
        BRACKET_GT = new MlTokenElementType("BRACKET_GT", RmlLanguage.INSTANCE);
        BRACKET_LT = new MlTokenElementType("BRACKET_LT", RmlLanguage.INSTANCE);
        BRACE_LT = new MlTokenElementType("BRACE_LT", RmlLanguage.INSTANCE);

        ML_STRING_OPEN = new MlTokenElementType("ML_STRING_OPEN", RmlLanguage.INSTANCE);
        ML_STRING_CLOSE = new MlTokenElementType("ML_STRING_CLOSE", RmlLanguage.INSTANCE);
        JS_STRING_OPEN = new MlTokenElementType("JS_STRING_OPEN", RmlLanguage.INSTANCE);
        JS_STRING_CLOSE = new MlTokenElementType("JS_STRING_CLOSE", RmlLanguage.INSTANCE);
        UNDERSCORE = new MlTokenElementType("UNDERSCORE", RmlLanguage.INSTANCE);
    }
}