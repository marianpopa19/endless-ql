// Generated from QL.g by ANTLR 4.7.1

	package org.uva.jomi.ql.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, LABEL=8, Type=9, 
		LINE_COMMENT=10, COMMENT=11, WS=12, IDENTIFIER=13, STRING=14, BOOLEAN=15, 
		INTEGER=16, DECIMAL=17, DATE=18, MONEY=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "LABEL", "Type", 
		"LINE_COMMENT", "COMMENT", "WS", "IDENTIFIER", "STRING", "BOOLEAN", "INTEGER", 
		"DECIMAL", "DATE", "MONEY"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "':'", null, null, 
		null, null, null, null, "'string'", "'boolean'", "'integer'", "'decimal'", 
		"'date'", "'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "LABEL", "Type", "LINE_COMMENT", 
		"COMMENT", "WS", "IDENTIFIER", "STRING", "BOOLEAN", "INTEGER", "DECIMAL", 
		"DATE", "MONEY"
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


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u00a1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\7\t>\n\t\f\t\16\tA\13\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\nK\n\n\3\13\3\13\3\13\3\13\7\13Q\n\13\f\13\16"+
		"\13T\13\13\3\13\5\13W\n\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\fa\n"+
		"\f\f\f\16\fd\13\f\3\f\3\f\3\f\3\f\3\f\3\r\6\rl\n\r\r\r\16\rm\3\r\3\r\3"+
		"\16\3\16\6\16t\n\16\r\16\16\16u\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\5?Rb\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\5\5"+
		"\2\13\f\17\17\"\"\4\2C\\c|\5\2\62;C\\c|\2\u00ab\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\3)\3\2\2\2\5.\3\2\2\2\7\60\3\2\2\2\t\62\3\2\2\2\13\65\3\2\2\2\r"+
		"\67\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23J\3\2\2\2\25L\3\2\2\2\27\\\3\2\2"+
		"\2\31k\3\2\2\2\33q\3\2\2\2\35w\3\2\2\2\37~\3\2\2\2!\u0086\3\2\2\2#\u008e"+
		"\3\2\2\2%\u0096\3\2\2\2\'\u009b\3\2\2\2)*\7h\2\2*+\7q\2\2+,\7t\2\2,-\7"+
		"o\2\2-\4\3\2\2\2./\7}\2\2/\6\3\2\2\2\60\61\7\177\2\2\61\b\3\2\2\2\62\63"+
		"\7k\2\2\63\64\7h\2\2\64\n\3\2\2\2\65\66\7*\2\2\66\f\3\2\2\2\678\7+\2\2"+
		"8\16\3\2\2\29:\7<\2\2:\20\3\2\2\2;?\7$\2\2<>\13\2\2\2=<\3\2\2\2>A\3\2"+
		"\2\2?@\3\2\2\2?=\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7$\2\2C\22\3\2\2\2DK\5"+
		"\35\17\2EK\5\37\20\2FK\5!\21\2GK\5#\22\2HK\5%\23\2IK\5\'\24\2JD\3\2\2"+
		"\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\24\3\2\2\2LM\7\61"+
		"\2\2MN\7\61\2\2NR\3\2\2\2OQ\13\2\2\2PO\3\2\2\2QT\3\2\2\2RS\3\2\2\2RP\3"+
		"\2\2\2SV\3\2\2\2TR\3\2\2\2UW\7\17\2\2VU\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY"+
		"\7\f\2\2YZ\3\2\2\2Z[\b\13\2\2[\26\3\2\2\2\\]\7\61\2\2]^\7,\2\2^b\3\2\2"+
		"\2_a\13\2\2\2`_\3\2\2\2ad\3\2\2\2bc\3\2\2\2b`\3\2\2\2ce\3\2\2\2db\3\2"+
		"\2\2ef\7,\2\2fg\7\61\2\2gh\3\2\2\2hi\b\f\2\2i\30\3\2\2\2jl\t\2\2\2kj\3"+
		"\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\b\r\2\2p\32\3\2\2\2q"+
		"s\t\3\2\2rt\t\4\2\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2v\34\3\2\2"+
		"\2wx\7u\2\2xy\7v\2\2yz\7t\2\2z{\7k\2\2{|\7p\2\2|}\7i\2\2}\36\3\2\2\2~"+
		"\177\7d\2\2\177\u0080\7q\2\2\u0080\u0081\7q\2\2\u0081\u0082\7n\2\2\u0082"+
		"\u0083\7g\2\2\u0083\u0084\7c\2\2\u0084\u0085\7p\2\2\u0085 \3\2\2\2\u0086"+
		"\u0087\7k\2\2\u0087\u0088\7p\2\2\u0088\u0089\7v\2\2\u0089\u008a\7g\2\2"+
		"\u008a\u008b\7i\2\2\u008b\u008c\7g\2\2\u008c\u008d\7t\2\2\u008d\"\3\2"+
		"\2\2\u008e\u008f\7f\2\2\u008f\u0090\7g\2\2\u0090\u0091\7e\2\2\u0091\u0092"+
		"\7k\2\2\u0092\u0093\7o\2\2\u0093\u0094\7c\2\2\u0094\u0095\7n\2\2\u0095"+
		"$\3\2\2\2\u0096\u0097\7f\2\2\u0097\u0098\7c\2\2\u0098\u0099\7v\2\2\u0099"+
		"\u009a\7g\2\2\u009a&\3\2\2\2\u009b\u009c\7o\2\2\u009c\u009d\7q\2\2\u009d"+
		"\u009e\7p\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7{\2\2\u00a0(\3\2\2\2\n\2"+
		"?JRVbmu\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}