﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    internal class SymbolTable : ISymbolTable
    {
        private class VariableStorage
        {
            public VariableStorage(
                Type type, 
                object value)
            {
                Type = type;
                Value = value;
            }

            public Type Type { get;  }
            public object Value { get; set; }
        }

        private readonly Dictionary<Guid, VariableStorage> m_symbols = new Dictionary<Guid, VariableStorage>();

        public Type GetType(Guid variableRef)
        {
            if (!Exists(variableRef))
            {
                throw new ArgumentException(@"attempt to access a non existant variable");
            }

            return m_symbols[variableRef].Type;
        }

        public bool Exists(Guid variableRef)
        {
            return m_symbols.ContainsKey(variableRef);
        }
        
        public void Add<T>(Guid variableRef, T value)
        {
            ValidateType(typeof(T));
            if (m_symbols.ContainsKey(variableRef))
            {
                return;
            }

            m_symbols[variableRef] = new VariableStorage(typeof(T), value);
        }

        private void ValidateType(Type type)
        {
            var validTypes = new[] {typeof(int), typeof(decimal), typeof(bool), typeof(DateTime), typeof(string)};
            if (validTypes.All(x => x != type))
            {
                throw new ArgumentException($@"tried to process a variable of a the unsupported type '{type}'");
            }
        }

        public void Update<T>(Guid variableRef, T value)
        {
            ValidateType(typeof(T));
            if (m_symbols.ContainsKey(variableRef))
            {
                var newDataType = typeof(T);
                var originalDataType = m_symbols[variableRef].Type;
                m_symbols[variableRef].Value = TypeConvert(
                    newDataType, 
                    originalDataType, 
                    value);
            }
            else
            {
                Add(variableRef, value);
            }
        }

        private object TypeConvert<T>(Type newDataType, Type originalDataType, T value)
        {
            if (newDataType == originalDataType)
            {
                return value;
            }

            if (newDataType.IsSubclassOf(originalDataType))
            {
                return value;
            }

            if (newDataType == typeof(string))
            {
                if (originalDataType == typeof(int))
                {
                    return int.Parse(value as string);
                }

                if (originalDataType == typeof(DateTime))
                {
                    return DateTime.Parse(value as string, CultureInfo.InvariantCulture);
                }
                
                if (originalDataType == typeof(decimal))
                {
                    return decimal.Parse(value as string);
                }
            }

            throw new ArgumentException(
                $@"tried to put a type of '{newDataType}' with a value '{value}' into a variable of type {
                        originalDataType
                    }");
        }
        
        public bool Exists<T>(Guid variableRef)
        {
            var type = typeof(T);
            ValidateType(type);
            return m_symbols.Any(x => x.Key == variableRef && x.Value.Type == type);
        }

        public object Lookup(Guid variableRef)
        {
            return m_symbols
                .FirstOrDefault(x => x.Key == variableRef)
                .Value
                ?.Value;
        }

        public T Lookup<T>(Guid variableRef)
        {
            var type = typeof(T);
            ValidateType(type);
            var value = m_symbols
                .FirstOrDefault(x => x.Key == variableRef && x.Value.Type == type)
                .Value
                ?.Value;

            if (value == null)
            {
                Add(variableRef, default(T));
                value = default(T);
            }

            return (T) value;
        }
    }
}