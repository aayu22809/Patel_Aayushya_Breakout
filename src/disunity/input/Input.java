package disunity.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Every input type from any source
 * 
 * @author Qinzhao Li
 */
public enum Input {

    /* ================ [ VALUES ] ================ */
    
    // Escape key
    KEY_ESCAPE("Escape"),

    // Function keys
    KEY_F1("F1"), KEY_F2("F2"), KEY_F3("F3"), KEY_F4("F4"), KEY_F5("F5"), KEY_F6("F6"),
    KEY_F7("F7"), KEY_F8("F8"), KEY_F9("F9"), KEY_F10("F10"), KEY_F11("F11"), KEY_F12("F12"),

    // Grave key
    KEY_GRAVE("`"),

    // Number keys
    KEY_1("1"), KEY_2("2"), KEY_3("3"), KEY_4("4"), KEY_5("5"),
    KEY_6("6"), KEY_7("7"), KEY_8("8"), KEY_9("9"), KEY_0("0"),

    // Keyboard keys
    KEY_MINUS("-"), KEY_EQUALS("="),
    KEY_BACKSPACE("Backspace"), KEY_TAB("Tab"),
    KEY_Q("Q"), KEY_W("W"), KEY_E("E"), KEY_R("R"), KEY_T("T"),
    KEY_Y("Y"), KEY_U("U"), KEY_I("I"), KEY_O("O"), KEY_P("P"),
    KEY_LEFT_BRACKET("["), KEY_RIGHT_BRACKET("]"), KEY_BACKSLASH("\\"),
    KEY_CAPS_LOCK("Caps Lock"),
    KEY_A("A"), KEY_S("S"), KEY_D("D"), KEY_F("F"), KEY_G("G"),
    KEY_H("H"), KEY_J("J"), KEY_K("K"), KEY_L("L"),
    KEY_SEMICOLON(";"), KEY_APOSTROPHE("'"),
    KEY_ENTER("Enter"), KEY_SHIFT("Shift"),
    KEY_Z("Z"), KEY_X("X"), KEY_C("C"), KEY_V("V"), KEY_B("B"),
    KEY_N("N"), KEY_M("M"),
    KEY_COMMA(","), KEY_PERIOD("."), KEY_SLASH("/"),
    KEY_RIGHT_SHIFT("Right Shift"), KEY_FN("Fn"),
    KEY_CONTROL("Control"), KEY_ALT("Alt"), KEY_COMMAND("Command"),
    KEY_SPACE("Space"),
    KEY_RIGHT_COMMAND("Right Command"), KEY_RIGHT_ALT("Right Alt"), KEY_RIGHT_CONTROL("Right Control"),

    // Arrow keys
    KEY_LEFT("Left"), KEY_UP("Up"), KEY_RIGHT("Right"), KEY_DOWN("Down"),

    // Windows keys
    KEY_WINDOWS("Windows"), KEY_CONTEXT_MENU("Menu"),
    KEY_INSERT("Insert"), KEY_DELETE("Delete"),
    KEY_HOME("Home"), KEY_END("End"),
    KEY_PAGE_UP("Page Up"), KEY_PAGE_DOWN("Page Down"),

    // Numpad keys
    KEY_NUM_LOCK("Num Lock"),
    KEY_NP_0("Numpad 0"), KEY_NP_1("Numpad 1"), KEY_NP_2("Numpad 2"), KEY_NP_3("Numpad 3"),
    KEY_NP_4("Numpad 4"), KEY_NP_5("Numpad 5"), KEY_NP_6("Numpad 6"), KEY_NP_7("Numpad 7"),
    KEY_NP_8("Numpad 8"), KEY_NP_9("Numpad 9"),
    KEY_NP_SLASH("Numpad /"), KEY_NP_ASTERISK("Numpad *"), KEY_NP_MINUS("Numpad -"),
    KEY_NP_PLUS("Numpad +"), KEY_NP_PERIOD("Numpad ."),
    KEY_NP_ENTER("Numpad Enter"),

    // Mouse buttons
    MOUSE_LEFT("Left Click"), MOUSE_RIGHT("Right Click"), MOUSE_MIDDLE("Middle Click");

    /* ================ [ FIELDS ] ================ */

    // Name
    private String name;

    // Constructor
    Input(String name) { this.name = name; }

    /* ================ [ METHODS ] ================ */

    // Get name
    public String getName() { return name; }

    // Get from key
    public static Input getFromKey(int key) {
        switch (key) {
            case KeyEvent.VK_ESCAPE: return KEY_ESCAPE;
            case KeyEvent.VK_F1: return KEY_F1;
            case KeyEvent.VK_F2: return KEY_F2;
            case KeyEvent.VK_F3: return KEY_F3;
            case KeyEvent.VK_F4: return KEY_F4;
            case KeyEvent.VK_F5: return KEY_F5;
            case KeyEvent.VK_F6: return KEY_F6;
            case KeyEvent.VK_F7: return KEY_F7;
            case KeyEvent.VK_F8: return KEY_F8;
            case KeyEvent.VK_F9: return KEY_F9;
            case KeyEvent.VK_F10: return KEY_F10;
            case KeyEvent.VK_F11: return KEY_F11;
            case KeyEvent.VK_F12: return KEY_F12;
            case KeyEvent.VK_BACK_QUOTE: return KEY_GRAVE;
            case KeyEvent.VK_1: return KEY_1;
            case KeyEvent.VK_2: return KEY_2;
            case KeyEvent.VK_3: return KEY_3;
            case KeyEvent.VK_4: return KEY_4;
            case KeyEvent.VK_5: return KEY_5;
            case KeyEvent.VK_6: return KEY_6;
            case KeyEvent.VK_7: return KEY_7;
            case KeyEvent.VK_8: return KEY_8;
            case KeyEvent.VK_9: return KEY_9;
            case KeyEvent.VK_0: return KEY_0;
            case KeyEvent.VK_MINUS: return KEY_MINUS;
            case KeyEvent.VK_EQUALS: return KEY_EQUALS;
            case KeyEvent.VK_BACK_SPACE: return KEY_BACKSPACE;
            case KeyEvent.VK_TAB: return KEY_TAB;
            case KeyEvent.VK_Q: return KEY_Q;
            case KeyEvent.VK_W: return KEY_W;
            case KeyEvent.VK_E: return KEY_E;
            case KeyEvent.VK_R: return KEY_R;
            case KeyEvent.VK_T: return KEY_T;
            case KeyEvent.VK_Y: return KEY_Y;
            case KeyEvent.VK_U: return KEY_U;
            case KeyEvent.VK_I: return KEY_I;
            case KeyEvent.VK_O: return KEY_O;
            case KeyEvent.VK_P: return KEY_P;
            case KeyEvent.VK_OPEN_BRACKET: return KEY_LEFT_BRACKET;
            case KeyEvent.VK_CLOSE_BRACKET: return KEY_RIGHT_BRACKET;
            case KeyEvent.VK_BACK_SLASH: return KEY_BACKSLASH;
            case KeyEvent.VK_CAPS_LOCK: return KEY_CAPS_LOCK;
            case KeyEvent.VK_A: return KEY_A;
            case KeyEvent.VK_S: return KEY_S;
            case KeyEvent.VK_D: return KEY_D;
            case KeyEvent.VK_F: return KEY_F;
            case KeyEvent.VK_G: return KEY_G;
            case KeyEvent.VK_H: return KEY_H;
            case KeyEvent.VK_J: return KEY_J;
            case KeyEvent.VK_K: return KEY_K;
            case KeyEvent.VK_L: return KEY_L;
            case KeyEvent.VK_SEMICOLON: return KEY_SEMICOLON;
            case KeyEvent.VK_QUOTE: return KEY_APOSTROPHE;
            case KeyEvent.VK_ENTER: return KEY_ENTER;
            case KeyEvent.VK_SHIFT: return KEY_SHIFT;
            case KeyEvent.VK_Z: return KEY_Z;
            case KeyEvent.VK_X: return KEY_X;
            case KeyEvent.VK_C: return KEY_C;
            case KeyEvent.VK_V: return KEY_V;
            case KeyEvent.VK_B: return KEY_B;
            case KeyEvent.VK_N: return KEY_N;
            case KeyEvent.VK_M: return KEY_M;
            case KeyEvent.VK_COMMA: return KEY_COMMA;
            case KeyEvent.VK_PERIOD: return KEY_PERIOD;
            case KeyEvent.VK_SLASH: return KEY_SLASH;
            // case KeyEvent.RIGHT_SHIFT: return KEY_RIGHT_SHIFT;
            // case KeyEvent.VK_FN: return KEY_FN;
            case KeyEvent.VK_CONTROL: return KEY_CONTROL;
            case KeyEvent.VK_ALT: return KEY_ALT;
            case KeyEvent.VK_META: return KEY_COMMAND;
            case KeyEvent.VK_SPACE: return KEY_SPACE;
            // case KeyEvent.VK_RIGHT_COMMAND: return KEY_RIGHT_COMMAND;
            // case KeyEvent.VK_RIGHT_ALT: return KEY_RIGHT_ALT;
            // case KeyEvent.VK_RIGHT_CONTROL: return KEY_RIGHT_CONTROL;
            case KeyEvent.VK_LEFT: return KEY_LEFT;
            case KeyEvent.VK_UP: return KEY_UP;
            case KeyEvent.VK_RIGHT: return KEY_RIGHT;
            case KeyEvent.VK_DOWN: return KEY_DOWN;
            case KeyEvent.VK_WINDOWS: return KEY_WINDOWS;
            case KeyEvent.VK_CONTEXT_MENU: return KEY_CONTEXT_MENU;
            case KeyEvent.VK_INSERT: return KEY_INSERT;
            case KeyEvent.VK_DELETE: return KEY_DELETE;
            case KeyEvent.VK_HOME: return KEY_HOME;
            case KeyEvent.VK_END: return KEY_END;
            case KeyEvent.VK_PAGE_UP: return KEY_PAGE_UP;
            case KeyEvent.VK_PAGE_DOWN: return KEY_PAGE_DOWN;
            case KeyEvent.VK_NUM_LOCK: return KEY_NUM_LOCK;
            case KeyEvent.VK_NUMPAD0: return KEY_NP_0;
            case KeyEvent.VK_NUMPAD1: return KEY_NP_1;
            case KeyEvent.VK_NUMPAD2: return KEY_NP_2;
            case KeyEvent.VK_NUMPAD3: return KEY_NP_3;
            case KeyEvent.VK_NUMPAD4: return KEY_NP_4;
            case KeyEvent.VK_NUMPAD5: return KEY_NP_5;
            case KeyEvent.VK_NUMPAD6: return KEY_NP_6;
            case KeyEvent.VK_NUMPAD7: return KEY_NP_7;
            case KeyEvent.VK_NUMPAD8: return KEY_NP_8;
            case KeyEvent.VK_NUMPAD9: return KEY_NP_9;
            // case KeyEvent.VK_NP_SLASH: return KEY_NP_SLASH;
            // case KeyEvent.VK_NP_ASTERISK: return KEY_NP_ASTERISK;
            // case KeyEvent.VK_NP_MINUS: return KEY_NP_MINUS;
            // case KeyEvent.VK_NP_PLUS: return KEY_NP_PLUS;
            // case KeyEvent.VK_NP_PERIOD: return KEY_NP_PERIOD;
            // case KeyEvent.VK_NP_ENTER: return KEY_NP_ENTER;
            default: return null;
        }
    }

    // Get from mouse
    public static Input getFromMouse(int button) {
        switch (button) {
            case MouseEvent.BUTTON1: return MOUSE_LEFT;
            case MouseEvent.BUTTON2: return MOUSE_RIGHT;
            case MouseEvent.BUTTON3: return MOUSE_MIDDLE;
            default: return null;
        }
    }

}
