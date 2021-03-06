// Generated from QLS.g4 by ANTLR 4.7.1

	package org.uva.jomi.qls.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, TYPE=19, IDENTIFIER=20, LABEL=21, LINE_COMMENT=22, COMMENT=23, 
		WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "TYPE", "IDENTIFIER", "LABEL", "LINE_COMMENT", "COMMENT", "WS", 
		"DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'question'", 
		"'default'", "'widget'", "'radio'", "'('", "','", "')'", "'spinbox'", 
		"'text'", "'yesno-radios'", "'checkbox'", "'dropdown'", "'yesno-dropdown'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "TYPE", "IDENTIFIER", "LABEL", 
		"LINE_COMMENT", "COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public QLSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u0113\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00d7"+
		"\n\24\3\25\6\25\u00da\n\25\r\25\16\25\u00db\3\25\3\25\7\25\u00e0\n\25"+
		"\f\25\16\25\u00e3\13\25\3\26\3\26\7\26\u00e7\n\26\f\26\16\26\u00ea\13"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u00f2\n\27\f\27\16\27\u00f5\13"+
		"\27\3\27\5\27\u00f8\n\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30"+
		"\u0102\n\30\f\30\16\30\u0105\13\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\5\u00e8\u00f3\u0103\2\34\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\2\65\2\3\2\5\5\2\13\f\17\17\"\"\3\2"+
		"\62;\4\2C\\c|\2\u011c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\67\3\2\2\2\5B\3\2\2\2\7D\3\2\2"+
		"\2\tF\3\2\2\2\13K\3\2\2\2\rS\3\2\2\2\17\\\3\2\2\2\21d\3\2\2\2\23k\3\2"+
		"\2\2\25q\3\2\2\2\27s\3\2\2\2\31u\3\2\2\2\33w\3\2\2\2\35\177\3\2\2\2\37"+
		"\u0084\3\2\2\2!\u0091\3\2\2\2#\u009a\3\2\2\2%\u00a3\3\2\2\2\'\u00d6\3"+
		"\2\2\2)\u00d9\3\2\2\2+\u00e4\3\2\2\2-\u00ed\3\2\2\2/\u00fd\3\2\2\2\61"+
		"\u010b\3\2\2\2\63\u010f\3\2\2\2\65\u0111\3\2\2\2\678\7u\2\289\7v\2\29"+
		":\7{\2\2:;\7n\2\2;<\7g\2\2<=\7u\2\2=>\7j\2\2>?\7g\2\2?@\7g\2\2@A\7v\2"+
		"\2A\4\3\2\2\2BC\7}\2\2C\6\3\2\2\2DE\7\177\2\2E\b\3\2\2\2FG\7r\2\2GH\7"+
		"c\2\2HI\7i\2\2IJ\7g\2\2J\n\3\2\2\2KL\7u\2\2LM\7g\2\2MN\7e\2\2NO\7v\2\2"+
		"OP\7k\2\2PQ\7q\2\2QR\7p\2\2R\f\3\2\2\2ST\7s\2\2TU\7w\2\2UV\7g\2\2VW\7"+
		"u\2\2WX\7v\2\2XY\7k\2\2YZ\7q\2\2Z[\7p\2\2[\16\3\2\2\2\\]\7f\2\2]^\7g\2"+
		"\2^_\7h\2\2_`\7c\2\2`a\7w\2\2ab\7n\2\2bc\7v\2\2c\20\3\2\2\2de\7y\2\2e"+
		"f\7k\2\2fg\7f\2\2gh\7i\2\2hi\7g\2\2ij\7v\2\2j\22\3\2\2\2kl\7t\2\2lm\7"+
		"c\2\2mn\7f\2\2no\7k\2\2op\7q\2\2p\24\3\2\2\2qr\7*\2\2r\26\3\2\2\2st\7"+
		".\2\2t\30\3\2\2\2uv\7+\2\2v\32\3\2\2\2wx\7u\2\2xy\7r\2\2yz\7k\2\2z{\7"+
		"p\2\2{|\7d\2\2|}\7q\2\2}~\7z\2\2~\34\3\2\2\2\177\u0080\7v\2\2\u0080\u0081"+
		"\7g\2\2\u0081\u0082\7z\2\2\u0082\u0083\7v\2\2\u0083\36\3\2\2\2\u0084\u0085"+
		"\7{\2\2\u0085\u0086\7g\2\2\u0086\u0087\7u\2\2\u0087\u0088\7p\2\2\u0088"+
		"\u0089\7q\2\2\u0089\u008a\7/\2\2\u008a\u008b\7t\2\2\u008b\u008c\7c\2\2"+
		"\u008c\u008d\7f\2\2\u008d\u008e\7k\2\2\u008e\u008f\7q\2\2\u008f\u0090"+
		"\7u\2\2\u0090 \3\2\2\2\u0091\u0092\7e\2\2\u0092\u0093\7j\2\2\u0093\u0094"+
		"\7g\2\2\u0094\u0095\7e\2\2\u0095\u0096\7m\2\2\u0096\u0097\7d\2\2\u0097"+
		"\u0098\7q\2\2\u0098\u0099\7z\2\2\u0099\"\3\2\2\2\u009a\u009b\7f\2\2\u009b"+
		"\u009c\7t\2\2\u009c\u009d\7q\2\2\u009d\u009e\7r\2\2\u009e\u009f\7f\2\2"+
		"\u009f\u00a0\7q\2\2\u00a0\u00a1\7y\2\2\u00a1\u00a2\7p\2\2\u00a2$\3\2\2"+
		"\2\u00a3\u00a4\7{\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7"+
		"\7p\2\2\u00a7\u00a8\7q\2\2\u00a8\u00a9\7/\2\2\u00a9\u00aa\7f\2\2\u00aa"+
		"\u00ab\7t\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7r\2\2\u00ad\u00ae\7f\2\2"+
		"\u00ae\u00af\7q\2\2\u00af\u00b0\7y\2\2\u00b0\u00b1\7p\2\2\u00b1&\3\2\2"+
		"\2\u00b2\u00b3\7d\2\2\u00b3\u00b4\7q\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6"+
		"\7n\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7c\2\2\u00b8\u00d7\7p\2\2\u00b9"+
		"\u00ba\7u\2\2\u00ba\u00bb\7v\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7k\2\2"+
		"\u00bd\u00be\7p\2\2\u00be\u00d7\7i\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1"+
		"\7p\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7i\2\2\u00c4"+
		"\u00c5\7g\2\2\u00c5\u00d7\7t\2\2\u00c6\u00c7\7f\2\2\u00c7\u00c8\7g\2\2"+
		"\u00c8\u00c9\7e\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7o\2\2\u00cb\u00cc"+
		"\7c\2\2\u00cc\u00d7\7n\2\2\u00cd\u00ce\7f\2\2\u00ce\u00cf\7c\2\2\u00cf"+
		"\u00d0\7v\2\2\u00d0\u00d7\7g\2\2\u00d1\u00d2\7o\2\2\u00d2\u00d3\7q\2\2"+
		"\u00d3\u00d4\7p\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d7\7{\2\2\u00d6\u00b2"+
		"\3\2\2\2\u00d6\u00b9\3\2\2\2\u00d6\u00bf\3\2\2\2\u00d6\u00c6\3\2\2\2\u00d6"+
		"\u00cd\3\2\2\2\u00d6\u00d1\3\2\2\2\u00d7(\3\2\2\2\u00d8\u00da\5\65\33"+
		"\2\u00d9\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc"+
		"\3\2\2\2\u00dc\u00e1\3\2\2\2\u00dd\u00e0\5\65\33\2\u00de\u00e0\5\63\32"+
		"\2\u00df\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df"+
		"\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2*\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4"+
		"\u00e8\7$\2\2\u00e5\u00e7\13\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ec\7$\2\2\u00ec,\3\2\2\2\u00ed\u00ee\7\61\2\2"+
		"\u00ee\u00ef\7\61\2\2\u00ef\u00f3\3\2\2\2\u00f0\u00f2\13\2\2\2\u00f1\u00f0"+
		"\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4"+
		"\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f8\7\17\2\2\u00f7\u00f6\3"+
		"\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7\f\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\b\27\2\2\u00fc.\3\2\2\2\u00fd\u00fe\7\61\2"+
		"\2\u00fe\u00ff\7,\2\2\u00ff\u0103\3\2\2\2\u0100\u0102\13\2\2\2\u0101\u0100"+
		"\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u0106\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\7,\2\2\u0107\u0108\7\61"+
		"\2\2\u0108\u0109\3\2\2\2\u0109\u010a\b\30\2\2\u010a\60\3\2\2\2\u010b\u010c"+
		"\t\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\b\31\2\2\u010e\62\3\2\2\2\u010f"+
		"\u0110\t\3\2\2\u0110\64\3\2\2\2\u0111\u0112\t\4\2\2\u0112\66\3\2\2\2\13"+
		"\2\u00d6\u00db\u00df\u00e1\u00e8\u00f3\u00f7\u0103\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}