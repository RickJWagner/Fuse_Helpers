<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:math="http://www.w3.org/2005/xpath-functions/math"
    exclude-result-prefixes="xs math"
    version="3.0">

    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:mode on-no-match="shallow-copy"/>

    <xsl:template match="data">
        <xsl:copy>
            <xsl:apply-templates select="parse-json(.)?*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match=".[. instance of map(xs:string, item())]">
        <id name="{.?name}">
            <xsl:value-of select=".?id"/>
        </id>
    </xsl:template>

</xsl:stylesheet>