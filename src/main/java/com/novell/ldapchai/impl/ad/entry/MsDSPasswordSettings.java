/*
 * LDAP Chai API
 * Copyright (c) 2006-2017 Novell, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.novell.ldapchai.impl.ad.entry;

import com.novell.ldapchai.ChaiConstant;
import com.novell.ldapchai.ChaiPasswordPolicy;
import com.novell.ldapchai.ChaiPasswordRule;

public interface MsDSPasswordSettings extends Top, ChaiPasswordPolicy
{

    /**
     * All attributes used by the password policy.  Several "helper" values for each attribute are available, such as the ldap attribute name,
     * and default values.
     */
    enum Attribute
    {
        MSDS_PASSWORD_SETTINGS_PRECEDENCE(
                TYPE.MIN,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_PRECEDENCE,
                "0",
                null ),

        MSDS_PASSWORD_REVERSIBLE_ENCRYPTION(
                TYPE.BOOLEAN,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_REVERSIBLE_ENCRYPTION,
                "FALSE",
                null ),

        MSDS_PASSWORD_HISTORY_LENGTH(
                TYPE.MIN,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_HISTORY_LENGTH,
                "0",
                null ),

        MSDS_PASSWORD_HISTORY_COMPLEXITY_ENABLED(
                TYPE.BOOLEAN,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_COMPLEXITY_ENABLED,
                "FALSE",
                ChaiPasswordRule.ADComplexity ),

        MSDS_PASSWORD_MIN_PASSWORD_LENGTH(
                TYPE.MIN,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_MIN_PASSWORD_LENGTH,
                "0",
                ChaiPasswordRule.MinimumLength ),

        MSDS_PASSWORD_MIN_PASSWORD_AGE(
                TYPE.DURATION,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_MIN_PASSWORD_AGE,
                "0",
                ChaiPasswordRule.MinimumLifetime ),

        MSDS_PASSWORD_MAX_PASSWORD_AGE(
                TYPE.DURATION,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_MAX_PASSWORD_AGE,
                "0",
                ChaiPasswordRule.ExpirationInterval ),

        MSDS_PASSWORD_LOCKOUT_THRESHOLD(
                TYPE.OTHER,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_LOCKOUT_THRESHOLD,
                "0",
                null ),

        MSDS_PASSWORD_LOCKOUT_WINDOW(
                TYPE.DURATION,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_LOCKOUT_WINDOW,
                "0",
                null ),

        MSDS_PASSWORD_LOCKOUT_DURATION(
                TYPE.DURATION,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_LOCKOUT_DURATION,
                "0",
                null ),

        MSDS_PASSWORD_APPLIES_TO(
                TYPE.OTHER,
                ChaiConstant.ATTR_AD_PASSWORD_POLICY_APPLIES_TO,
                "0",
                null ),;

        private final TYPE type;
        private final String ldapAttr;
        private final String defaultValue;
        private final ChaiPasswordRule ruleName;

        Attribute( final TYPE type, final String ldapAttr, final String defaultValue, final ChaiPasswordRule ruleName )
        {
            this.type = type;
            this.ldapAttr = ldapAttr;
            this.defaultValue = defaultValue;
            this.ruleName = ruleName;
        }

        /**
         * A string value useful for debugging.
         *
         * @return A string value useful for debugging.
         */
        public ChaiPasswordRule getRuleName()
        {
            return ruleName;
        }

        /**
         * Get the type of value that should be expected when working with this attribute's values.
         *
         * @return An enumeration indicating the type of value to be expected when working with this attributes values.
         */
        public TYPE getType()
        {
            return type;
        }

        /**
         * The string key value used in this {@link MsDSPasswordSettings} object's backing {@code Properties}.
         * Typically the same as {@link #getLdapAttribute()}, but this is not guaranteed
         *
         * @return A String useful for managing a map of {@link MsDSPasswordSettings} values.
         */
        public String getKey()
        {
            return ldapAttr;
        }

        /**
         * The appropriate ldap attribute name of the attribute.
         * Typically the same as {@link #getKey()}, but this is not guaranteed
         *
         * @return An ldap attribute name
         */
        public String getLdapAttribute()
        {
            return ldapAttr;
        }

        /**
         * Default value used by for this attribute.
         *
         * @return The String value of the default value
         */
        public String getDefaultValue()
        {
            return defaultValue;
        }

        /**
         * An enumeration indicating what type of setting is expected for this attribute's value.
         */
        public enum TYPE
        {
            /**
             * An integer representing a maximum limit of a value
             */MAX,
            /**
             * An integer representing a minimum limit of a value
             */MIN,
            /**
             * An boolean representing an on/off value
             */BOOLEAN,
            /**
             * An time duration
             */DURATION,
            /**
             * Some other type of value
             */OTHER
        }

        public static Attribute attributeForRule( final ChaiPasswordRule rule )
        {
            if ( rule == null )
            {
                return null;
            }

            for ( final Attribute attr : Attribute.values() )
            {
                if ( rule.equals( attr.getRuleName() ) )
                {
                    return attr;
                }
            }

            return null;
        }
    }
}
