package com.bsstokes.acme.test

import androidx.compose.ui.test.junit4.ComposeContentTestRule

/**
 * Extension to [ComposeContentTestRule] that allows a test function to be written like this:
 * ```
 * @Test
 * fun `assert something`() = composeTestRule.test {
 *     setContent { }
 *     onNodeWithText("some text").assertExists()
 * }
 * ```
 * This cuts down on indentation and allows the block to use the [ComposeContentTestRule]'s members
 * implicitly.
 */
inline fun ComposeContentTestRule.test(block: ComposeContentTestRule.() -> Unit) = with(this, block)
