package org.carrot2.util.attribute.test.assertions;

import static org.fest.assertions.Assertions.assertThat;

import org.carrot2.util.attribute.CommonMetadata;

/**
 * Assertions on {@link CommonMetadata}.
 */
public class CommonMetadataAssertion
{
    private final CommonMetadata actual;

    public CommonMetadataAssertion(CommonMetadata actual)
    {
        this.actual = actual;
    }

    public CommonMetadataAssertion isEquivalentTo(CommonMetadata expected)
    {
        assertThat(actual.getDescription()).as("description").isEqualTo(
            expected.getDescription());
        assertThat(actual.getTitle()).as("title").isEqualTo(expected.getTitle());
        assertThat(actual.getLabel()).as("label").isEqualTo(expected.getLabel());
        return this;
    }
}
