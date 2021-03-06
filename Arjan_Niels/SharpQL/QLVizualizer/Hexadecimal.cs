﻿using System;
using System.Drawing;

namespace QLVisualizer
{
    public class Hexadecimal
    {
        private int _value;

        public Hexadecimal(int value)
        {
            _value = value;
        }

        public static Hexadecimal FromString(string value)
        {
            if (IsValid(value))
                return new Hexadecimal(StringToInt(value));
            else
                throw new InvalidCastException("Cannot parse hexadecimal value from: " + value);
        }

        private static bool IsValid(string value)
        {
            return value.StartsWith("#");
        }

        private static int StringToInt(string value)
        {
            return Convert.ToInt32(value.Substring(1), 16);
        }

        public override string ToString()
        {
            return string.Format("#{0}", _value.ToString("X4"));
        }

        public int ToInteger()
        {
            return _value;
        }

        public Color ToColor()
        {
            ColorConverter colorConverter = new ColorConverter();

            try
            {
                return (Color)colorConverter.ConvertFromString(ToString());
            }
            catch
            {
                throw new InvalidCastException(string.Format("Cannot convert hexadecimal value: {0} to a color", ToString()));
            }
        }

        public static Hexadecimal operator +(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value + right._value);
        }

        public static Hexadecimal operator -(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value - right._value);
        }

        public static Hexadecimal operator *(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value * right._value);
        }

        public static Hexadecimal operator /(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value / right._value);
        }

        public static bool operator <(Hexadecimal left, Hexadecimal right)
        {
            return left._value < right._value;
        }

        public static bool operator >(Hexadecimal left, Hexadecimal right)
        {
            return left._value > right._value;
        }
    }
}
