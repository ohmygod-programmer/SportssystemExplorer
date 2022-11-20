package com.zetcode

import actionlisteners.LandmarksListActionListener
import actionlisteners.LocationsListActionListener
import actionlisteners.RestartButtonListActionListener
import actionlisteners.StartScreenActionListener
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.awt.*
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.BoxLayout.Y_AXIS
import javax.swing.SwingConstants.CENTER
import javax.swing.border.EmptyBorder

class MainFrame(title: String) : JFrame() {
    private val FONT_SIZE = 30
    private val mainPanel = JPanel()
    private val loadingImageLabel = JLabel(ImageIcon("src/main/resources/loadgif.gif"))

    init {
        mainPanel.background = Color.WHITE
        layout = BorderLayout()
        setTitle(title)
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setSize(400, 500)
        setLocationRelativeTo(null)
        mainPanel.alignmentX = CENTER_ALIGNMENT
        mainPanel.setBorder(EmptyBorder(Insets(10, 10, 10, 10)))
        add(mainPanel, BorderLayout.CENTER)
        isVisible = true
    }

    fun loadingScreen() {
        reset()
        mainPanel.layout = BorderLayout()
        mainPanel.add(loadingImageLabel, BorderLayout.CENTER)
        loadingImageLabel.isVisible = true
        mainPanel.revalidate()
    }

    fun reset() {
        mainPanel.removeAll()
        mainPanel.revalidate()
    }

    fun startScreen() {
        reset()
        mainPanel.layout = BoxLayout(mainPanel, Y_AXIS)
        val textField = JTextField()
        textField.font = Font("TimesNewRoman", Font.PLAIN, FONT_SIZE)
        textField.preferredSize = Dimension(300, 2 * FONT_SIZE)
        textField.maximumSize = Dimension(500, 2 * FONT_SIZE)
        textField.alignmentX = CENTER_ALIGNMENT

        val button = JButton("Поиск")
        button.preferredSize = Dimension(50, 30)
        button.alignmentX = CENTER_ALIGNMENT
        val actionListener = StartScreenActionListener(this, textField)
        textField.addActionListener(actionListener)
        button.addActionListener(actionListener)
        mainPanel.add(Box.createVerticalGlue())
        mainPanel.add(textField)
        mainPanel.add(Box.Filler(Dimension(0, 5), Dimension(0, 10), Dimension(0, 15)))
        mainPanel.add(button)
        mainPanel.add(Box.createVerticalGlue())
        revalidate()
        repaint()
        textField.requestFocusInWindow()
    }

    private fun parseName(el: JsonElement): String {
        val obj: JsonObject
        try {
            obj = el.asJsonObject
        } catch (e: Exception) {
            println("Error")
            return "Error"
        }
        var text = ""
        if (obj.get("name") != null) {
            text += " " + obj.get("name").asString
        }
        if (obj.get("osm_value") != null) {
            text += " " + obj.get("osm_value").asString
        } else if (obj.get("osm_key") != null) {
            text += " " + obj.get("osm_key").asString
        }
        if (obj.get("city") != null) {
            text += ", " + obj.get("city").asString
        }
        if (obj.get("state") != null) {
            text += ", " + obj.get("state").asString
        }
        if (obj.get("country") != null) {
            text += ", " + obj.get("country").asString
        }
        return text
    }

    fun locationsList(jsonArray: JsonArray) {
        reset()
        mainPanel.layout = BoxLayout(mainPanel, Y_AXIS)
        for (el in jsonArray) {
            var button = JButton()
            button.text = parseName(el)
            button.alignmentX = CENTER_ALIGNMENT
            button.addActionListener(LocationsListActionListener(this, Gson().toJson(el)))
            mainPanel.add(button)
            button.minimumSize = Dimension(100, 10)
            mainPanel.add(Box.Filler(Dimension(0, 5), Dimension(0, 10), Dimension(0, 15)))
        }

        var restartButton = JButton()
        restartButton.text = "Try again"
        restartButton.background = Color.YELLOW
        restartButton.alignmentX = CENTER_ALIGNMENT
        restartButton.addActionListener(RestartButtonListActionListener(this))
        mainPanel.add(restartButton)

        mainPanel.revalidate()
        mainPanel.repaint()
    }

    private fun parseLandmarks(arr: JsonArray, panel: JPanel, max: Int) {
        var i = 0
        var count = 0
        while (i < arr.size() && count < max) {
            val obj: JsonObject
            val name: String
            try {
                obj = arr.get(i).asJsonObject
                name = obj.get("name").asString
            } catch (e: Exception) {
                continue
            }
            if (name.isNotEmpty() && obj.get("info") != null) {
                val button = JButton(name)
                button.alignmentX = CENTER_ALIGNMENT
                button.addActionListener(LandmarksListActionListener(this, obj))
                panel.add(button)
                count++
            }
            i++
        }
        i = 0
        while (i < arr.size() && count < max) {
            val obj: JsonObject
            val name: String
            try {
                obj = arr.get(i).asJsonObject
                name = obj.get("name").asString
            } catch (e: Exception) {
                continue
            }
            if (name.isNotEmpty()) {
                val button = JButton(name)
                button.alignmentX = CENTER_ALIGNMENT
                button.addActionListener(LandmarksListActionListener(this, obj))
                panel.add(button)
                count++
            }
            i++
        }
    }

    fun locationInfo(weatherHTML: String, landmarksJsonArray: JsonArray) {
        reset()
        mainPanel.layout = BoxLayout(mainPanel, Y_AXIS)
        mainPanel.alignmentX = CENTER_ALIGNMENT

        val htmlPanel = JEditorPane()
        htmlPanel.contentType = "text/html"
        htmlPanel.alignmentX = CENTER_ALIGNMENT
        htmlPanel.text = weatherHTML
        htmlPanel.maximumSize = Dimension(150, 300)
        htmlPanel.isEditable = false

        val textLabel = JLabel("Interesting places nearby:")
        textLabel.alignmentX = CENTER_ALIGNMENT
        textLabel.horizontalAlignment = CENTER

        val landmarksPanel = JPanel()
        landmarksPanel.layout = BoxLayout(landmarksPanel, Y_AXIS)
        landmarksPanel.alignmentX = CENTER_ALIGNMENT
        landmarksPanel.background = mainPanel.background
        parseLandmarks(landmarksJsonArray, landmarksPanel, 5)




        mainPanel.add(htmlPanel)
        mainPanel.add(landmarksPanel)


        mainPanel.revalidate()
        mainPanel.repaint()
    }

    private fun parseLandmark(pane: Container, json: JsonObject) {
        if (json.get("preview") != null) {
            try {
                val label =
                    JLabel(ImageIcon(ImageIO.read(URL(json.get("preview").asJsonObject.get("source").asString))))
                label.alignmentX = CENTER_ALIGNMENT
                pane.add(label)
            } catch (e: java.lang.Exception) {
            }
        }
        val text = JTextPane()
        text.contentType = "text/html"

        if (json.get("info") != null) {
            try {
                text.text = json.get("info").asJsonObject.get("descr").asString
            } catch (e: java.lang.Exception) {
                text.text = "No information"
            }
        } else if (json.get("wikipedia_extracts") != null) {
            try {
                text.text = json.get("wikipedia_extracts").asJsonObject.get("text").asString
            } catch (e: java.lang.Exception) {
                text.text = "No information"
            }
        } else {
            text.text = "No information"
        }
        text.isEditable = false
        pane.add(text)
    }

    fun landmarkInfo(landmarkJson: JsonObject) {
        reset()
        mainPanel.layout = BoxLayout(mainPanel, Y_AXIS)
        mainPanel.alignmentX = CENTER_ALIGNMENT
        val restartButton = JButton("restart")
        restartButton.addActionListener(RestartButtonListActionListener(this))
        mainPanel.add(restartButton)
        val panel = JPanel()
        parseLandmark(mainPanel, landmarkJson)

        mainPanel.add(panel)

        mainPanel.revalidate()
        mainPanel.repaint()
    }


}

