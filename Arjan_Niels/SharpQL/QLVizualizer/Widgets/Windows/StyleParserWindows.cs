﻿using QLParser.AST.QL;
using System;
using System.Collections.Generic;

namespace QLVisualizer.Widgets.Windows
{
    public class StyleParserWindows : IStyleParser
    {
        private static Dictionary<string, Tuple<StyleProperty, QValueType>> _supportedStyleProperties
        {
            get
            {
                return new Dictionary<string, Tuple<StyleProperty, QValueType>>()
                {
                    {"Height", new Tuple<StyleProperty, QValueType>(StyleProperty.Height,                   QValueType.INTEGER) },
                    {"Width", new Tuple<StyleProperty, QValueType>(StyleProperty.Width,                     QValueType.INTEGER) },
                    {"Margin-Top", new Tuple<StyleProperty, QValueType>(StyleProperty.TopMargin,            QValueType.INTEGER) },
                    {"Margin-Bottom", new Tuple<StyleProperty, QValueType>(StyleProperty.BottomMargin,      QValueType.INTEGER) },
                    {"BackgroundColor", new Tuple<StyleProperty, QValueType>(StyleProperty.BackgroundColor, QValueType.HEX) },
                    {"Font", new Tuple<StyleProperty, QValueType>(StyleProperty.Font,                       QValueType.TEXT) },
                    {"FontSize", new Tuple<StyleProperty, QValueType>(StyleProperty.FontSize,               QValueType.INTEGER) },
                    {"TextColor", new Tuple<StyleProperty, QValueType>(StyleProperty.TextColor,             QValueType.HEX) }
                };
            }
        }

        public Dictionary<string, string> ParseStyle()
        {
            throw new NotImplementedException();
        }
    }
}
